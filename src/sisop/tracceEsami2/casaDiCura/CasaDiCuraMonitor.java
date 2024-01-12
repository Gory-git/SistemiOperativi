package sisop.tracceEsami2.casaDiCura;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaDiCuraMonitor extends CasaDiCura
{
    private Lock monitor = new ReentrantLock();
    private Condition ingresso = monitor.newCondition();
    private Condition attesa = monitor.newCondition();
    private LinkedList<Thread> codaAttesa = new LinkedList<>();
    private boolean possoEntrare = false;
    private Condition operare = monitor.newCondition();
    private boolean possoOperare = false;
    private Condition uscire = monitor.newCondition();
    private boolean possoUscire = false;

    public CasaDiCuraMonitor(int postiSalaAttesa)
    {
        super(postiSalaAttesa);
    }

    @Override
    public void chiamaEIniziaOperazione() throws Exception
    {
        monitor.lock();
        try
        {
            possoEntrare = true;
            attesa.signalAll();
            while (!possoOperare)
                operare.await();
            possoOperare = false;
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void fineOperazione() throws Exception
    {
        monitor.lock();
        try
        {
            possoUscire = true;
            uscire.signal();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void pazienteEntra() throws Exception
    {
        monitor.lock();
        try
        {
            while (contatoreAttesa == 3)
                ingresso.await();
            contatoreAttesa++;

            codaAttesa.add(Thread.currentThread());
            while(!codaAttesa.getFirst().equals(Thread.currentThread()) || !possoEntrare)
                attesa.await();
            possoEntrare = false;
            contatoreAttesa--;
            possoOperare = true;
            operare.signal();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void pazienteEsci() throws Exception
    {
        monitor.lock();
        try
        {
            while (!possoUscire)
                uscire.await();
            possoUscire = false;
        }finally
        {
            monitor.unlock();
        }
    }
}
