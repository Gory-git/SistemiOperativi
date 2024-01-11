package sisop.tracceEsami1.barMod1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarModMonitor extends BarMod
{
    private Lock monitor = new ReentrantLock(true);

    private Condition filaBancone = monitor.newCondition();
    private Condition filaCassa = monitor.newCondition();

    private List<Thread> listaBancone = new LinkedList<>();
    private List<Thread> listaCassa = new LinkedList<>();

    public BarModMonitor(int postiBancone)
    {
        super(postiBancone);
    }

    @Override
    public int scegli()
    {
        int codaB = 0;
        int codaC = 0;
        monitor.lock();
        try
        {
            codaB = listaBancone.size() - permessiBanconeDisponibili;
            codaC = listaCassa.size() - 1;
        }finally
        {
            monitor.unlock();
        }
        if(codaB == codaC)
            return random.nextInt(0, 2);
        if(codaB > codaC)
            return 0;
        else
            return 1;
    }

    @Override
    public void inizia(int scelta)
    {
        Thread corrente = Thread.currentThread();
        if(scelta == 0)
        {
            System.out.println(corrente.getName() +" si sta mettendo in coda al bancone.");
            monitor.lock();
            try
            {
                listaBancone.add(corrente);
                codaBancone++;
                while (listaBancone.indexOf(corrente) > 4)
                    filaBancone.await();
                listaBancone.remove(corrente);

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                monitor.unlock();
            }
        }else
        {
            System.out.println(corrente.getName() + " si sta mettendo in coda alla cassa.");
            monitor.lock();
            try
            {
                listaCassa.add(corrente);
                codaCassa++;
                while (listaCassa.indexOf(corrente) > 0)
                    filaCassa.await();
                listaCassa.remove(corrente);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }finally
            {
                monitor.unlock();
            }
        }
    }

    @Override
    public void finisci(int scelta)
    {
        Thread corrente = Thread.currentThread();
        if(scelta == 0)
        {
            System.out.println(corrente.getName() + " ha terminato al bancone");
            monitor.lock();
            try
            {
                codaBancone--;
                filaBancone.signalAll();

            } finally
            {
                monitor.unlock();
            }
        }else
        {
            System.out.println(corrente.getName() + " ha terminato alla cassa");
            monitor.lock();
            try
            {
                codaCassa--;
                filaCassa.signalAll();
            }finally
            {
                monitor.unlock();
            }
        }
    }
}
