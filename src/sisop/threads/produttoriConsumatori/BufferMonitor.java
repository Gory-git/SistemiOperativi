package sisop.threads.produttoriConsumatori;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferMonitor extends Buffer
{
    private Lock monitor = new ReentrantLock();
    private Condition inserisci = monitor.newCondition();
    private Condition preleva = monitor.newCondition();


    public BufferMonitor(int disponibilita)
    {
        super(disponibilita);
    }

    @Override
    public void put(int value) throws Exception
    {
        monitor.lock();
        try
        {
            while (disponibilita >= limite)
                inserisci.await();
            buffer[limite-disponibilita] = value;
            disponibilita++;
            preleva.signalAll();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public int get() throws Exception
    {
        int ret = 0;
        monitor.lock();
        try
        {
            while (disponibilita == 0)
                preleva.await();
            disponibilita--;
            ret = buffer[limite - disponibilita];
            inserisci.signalAll();
        }finally
        {
            monitor.unlock();
        }
        return ret;
    }
}
