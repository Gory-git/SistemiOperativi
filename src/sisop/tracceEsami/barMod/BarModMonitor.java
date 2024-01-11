package sisop.tracceEsami.barMod;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarModMonitor extends BarMod
{
    private Lock cassa, bancone, mutex;
    private Condition banconePieno;
    private int postiLiberiBancone;
    public BarModMonitor(int lunghezzaBancone)
    {
        super(lunghezzaBancone);
        this.postiLiberiBancone = lunghezzaBancone;

        cassa = new ReentrantLock(true);
        bancone = new ReentrantLock(true);
        mutex = new ReentrantLock(true);

        banconePieno = bancone.newCondition();
    }

    @Override
    public int scegli()
    {
        if(filaBancone == filaCassa)
            return new Random().nextInt(0,2);
        else if (postiLiberiBancone < lunghezzaBancone)
            return BANCONE;
        else
            return CASSA;
    }

    @Override
    public void inizia(int i)
    {
        if(i == CASSA)
        {
            cassa.lock();
        }else
        {
            bancone.lock();
            mutex.lock();
            try
            {
                while (postiLiberiBancone == 0)
                    banconePieno.await();
                postiLiberiBancone--;
            }catch (Exception e)
            {
                e.printStackTrace();
            }finally
            {
                mutex.unlock();
            }
        }
    }

    @Override
    public void finisci(int i)
    {
        if(i == CASSA)
        {
            cassa.unlock();
        }else
        {
            mutex.lock();
            try
            {
                postiLiberiBancone++;
            }finally
            {
                mutex.unlock();
                bancone.unlock();
                banconePieno.signal();
            }
        }
    }
}
