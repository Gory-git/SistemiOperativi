package sisop.tracceEsami2.aziendaAgricola;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaMonitor extends AziendaAgricola
{
    private Lock monitor = new ReentrantLock();
    private Condition cassa = monitor.newCondition();
    private Condition magazzino = monitor.newCondition();
    private Condition magazziniere = monitor.newCondition();

    private LinkedList<Thread> codaCassa = new LinkedList<>();
    private LinkedList<Thread> codaMagazzino = new LinkedList<>();

    @Override
    public void paga(int numeroSacchi) throws Exception
    {
        monitor.lock();
        try
        {
            codaCassa.add(Thread.currentThread());
            while (!codaCassa.getFirst().equals(Thread.currentThread()))
                cassa.await();
            codaCassa.removeFirst();
            incasso += numeroSacchi * PREZZO;
            cassa.signalAll();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void prelevaSacco() throws Exception
    {
        monitor.lock();
        try
        {
            codaMagazzino.add(Thread.currentThread());
            while (!codaMagazzino.getFirst().equals(Thread.currentThread()))
                magazzino.await();
            codaMagazzino.removeFirst();
            sacchi--;
            if (sacchi == 0)
                magazziniere.signal();
            magazzino.signalAll();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void rifornisciMagazzino() throws Exception
    {
        monitor.lock();
        try
        {
            while (sacchi > 0)
                magazziniere.await();
            sacchi = NUMERO_SACCHI;
            magazzino.signalAll();
        }finally
        {
            monitor.unlock();
        }
    }
}
