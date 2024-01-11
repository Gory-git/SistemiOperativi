package sisop.tracceEsami1.muratori1;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaMonitor extends Casa
{
    private Lock monitor = new ReentrantLock();
    private LinkedList<Thread> codaMuratoriCemento = new LinkedList<>();
    private LinkedList<Thread> codaMuratoriMattoni = new LinkedList<>();

    private LinkedList<Thread>[] code = new LinkedList[]{codaMuratoriMattoni, codaMuratoriCemento};

    private Condition[]  condizioni = {monitor.newCondition(), monitor.newCondition()};
    private boolean[] possoMettere = {true, false};


    public CasaMonitor(int NUM_STRISCE, int NUM_PARETI)
    {
        super(NUM_STRISCE, NUM_PARETI);
    }

    @Override
    public boolean inizia(int t)
    {
        monitor.lock();
        try
        {
            if(muriCompletati >= NUM_PARETI)
            {
                possoMettere[0] = false;
                possoMettere[1] = false;
                return false;
            }
            Thread corrente = Thread.currentThread();
            code[t].add(corrente);
            while(code[t].getFirst() != corrente || !possoMettere[t])
                condizioni[t].await();
            code[t].removeFirst();
            livelliCompletati[t]++;
            possoMettere[t] = false;
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
        return(muriCompletati < NUM_PARETI);
    }

    @Override
    public void termina(int t)
    {
        monitor.lock();
        try
        {
            int i = 0;
            if(livelliCompletati[0] == NUM_STRISCE)
            {
                System.out.println("HO FINITO UN MURO");
                muriCompletati++;
                possoMettere[0] = true;
                possoMettere[1] = false;
                livelliCompletati[0] = 0;
                livelliCompletati[1] = 0;
            }else if(livelliCompletati[0] < NUM_STRISCE)
            {
                i = 1 - t;
                possoMettere[i] = true;
            }
            condizioni[i].signalAll();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
    }
}
