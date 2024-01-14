package sisop.tracceEsami2.bowling;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BowlingMonitor extends Bowling
{
    private Lock monitor = new ReentrantLock();
    private HashMap<Thread, Integer> giocatoreIndice = new HashMap<>();
    private Condition[] tirare;
    private LinkedList<Thread> codaTiri = new LinkedList<>();
    private Condition[] consegnare;
    private LinkedList<Thread> codaConsegnare = new LinkedList<>();

    protected BowlingMonitor(int numeroGiocatori, int tiriMassimi)
    {
        super(numeroGiocatori, tiriMassimi);
        for (int i = 0; i < numeroGiocatori; i++)
        {
            tirare[i] = monitor.newCondition();
            consegnare[i] = monitor.newCondition();
        }
    }

    @Override
    public String fornisciInformazioni() throws Exception
    {
        monitor.lock();
        try
        {

        }finally
        {
            monitor.unlock();
        }
        return null;
    }

    @Override
    public void preparaPartita() throws Exception
    {
        monitor.lock();
        try
        {

        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void gioca(String info) throws Exception
    {
        monitor.lock();
        try
        {

        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void deposita() throws Exception
    {
        monitor.lock();
        try
        {

        }finally
        {
            monitor.unlock();
        }
    }
}
