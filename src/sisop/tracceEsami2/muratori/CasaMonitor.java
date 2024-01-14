package sisop.tracceEsami2.muratori;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaMonitor extends Casa
{
    private Lock monitor = new ReentrantLock();
    private Condition[] lavoro = new Condition[]{monitor.newCondition(), monitor.newCondition()};
    private int[] contatore = {4, 0};
    private LinkedList<Thread>[] code = new LinkedList[]{new LinkedList(), new LinkedList<>()};
    public CasaMonitor(int numStrati)
    {
        super(numStrati);
    }

    @Override
    public void inizia(int tipo) throws Exception
    {
        monitor.lock();
        try
        {
            code[tipo].add(Thread.currentThread());
            while (!code[tipo].getFirst().equals(Thread.currentThread()) || contatore[tipo] == 0)
                lavoro[tipo].await();
            code[tipo].removeFirst();
            contatore[tipo]--;
            stratiFiniti[muroAttuale[tipo]%4] += tipo;
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void finisci(int tipo) throws Exception
    {
        monitor.lock();
        try
        {
            muroAttuale[tipo] += 1;
            contatore[1 - tipo] += 1;
            lavoro[1 - tipo].signal();
        }finally
        {
            monitor.unlock();
        }
    }
}
