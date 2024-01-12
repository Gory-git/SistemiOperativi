package sisop.tracceEsami2.barMod;

import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarModMonitor extends BarMod
{
    private Lock monitor = new ReentrantLock();
    private Condition[] attendi = new Condition[]{monitor.newCondition(), monitor.newCondition()};
    private LinkedList<Thread>[] code = new LinkedList[]{new LinkedList<>(), new LinkedList<>()};

    protected int personeAlBancone = 0;
    protected Thread[] bancone = new Thread[POSTI_BANCONE];
    public BarModMonitor()
    {
        for (int i = 0; i < POSTI_BANCONE; i++)
        {
            bancone[i] = null;
        }
    }
    @Override
    public int scegli() throws Exception
    {
        monitor.lock();
        try
        {
            int x = contatori[0];
            int y = contatori[1]-5;
            return x < y ? 0 : 1;
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void inizia(int scelta) throws Exception
    {
        monitor.lock();
        try
        {
            contatori[scelta]++;
            code[scelta].add(Thread.currentThread());
            while (nonPosso(scelta))
                attendi[scelta].await();
            Thread t = code[scelta].removeFirst();
            if(scelta == 1)
                bancone[personeAlBancone] = t;
            contatori[scelta] -= scelta;
            personeAlBancone += scelta;

        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void finisci(int scelta) throws Exception
    {
        monitor.lock();
        try
        {
            contatori[scelta] -= 1-scelta;
            personeAlBancone-= scelta;
            if(scelta == 1)
            {
                bancone[personeAlBancone] = null;
            }
            attendi[scelta].signalAll();
        }finally
        {
            monitor.unlock();
        }
    }

    private boolean nonPosso(int scelta)
    {
        boolean postiAlBancone = scelta == 1 ? personeAlBancone == POSTI_BANCONE : false;
        return !Thread.currentThread().equals(code[scelta].getFirst()) || postiAlBancone;
    }
}
