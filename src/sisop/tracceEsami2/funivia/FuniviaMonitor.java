package sisop.tracceEsami2.funivia;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FuniviaMonitor extends Funivia
{
    private Lock monitor = new ReentrantLock();
    private Condition possoPartire = monitor.newCondition();
    private Condition possoScendere = monitor.newCondition();
    private Condition[] aspetta = new Condition[]{monitor.newCondition(), monitor.newCondition()};
    private boolean[] salire = new boolean[]{false, false};
    private boolean scendere = false;
    @Override
    public void pilotaStart() throws Exception
    {
        monitor.lock();
        try
        {
            turno = 1 - turno;
            salire[turno] = true;
            aspetta[turno].signalAll();

            while (postiDisponibili > 0)
                possoPartire.await();

            salire[turno] = false;
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void pilotaEnd() throws Exception
    {
        monitor.lock();
        try
        {
            for(Thread passeggero : funivia)
                System.out.println("Scende il passeggero " + passeggero.getName());
            funivia.clear();
            scendere = true;
            possoScendere.signalAll();

        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void turistaSali(int tipo) throws Exception
    {
        int postiOccupo = 1 + tipo;
        monitor.lock();
        try
        {
            while (!salire[tipo] || postiOccupo > postiDisponibili )
                aspetta[tipo].await();
            funivia.add(Thread.currentThread());
            postiDisponibili -= postiOccupo;
            if (postiDisponibili == 0)
                possoPartire.signal();

        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void turistaScendi(int tipo) throws Exception
    {
        monitor.lock();
        try
        {
            int postiOccupo = 1 + tipo;
            while (!scendere)
                possoScendere.await();
            postiDisponibili += postiOccupo;
            if(postiDisponibili == POSTI_FUNIVIA)
                scendere = false;

        }finally
        {
            monitor.unlock();
        }
    }
}
