package sisop.tracceEsami.muratori;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaMonitor extends Casa
{

    private Lock monitor = new ReentrantLock();
    private Condition mattoni = monitor.newCondition();
    private Condition cemento = monitor.newCondition();

    public CasaMonitor(int NUN_STRISCE, int NUM_PARETI)
    {
        super(NUN_STRISCE, NUM_PARETI);
    }


    @Override
    public boolean inizia(int t)
    {
        monitor.lock();
        try
        {
            if(t == 0)
            {
                while(muriCemento < 4 && muriMattoni == 4)
                    mattoni.await();
                muriMattoni++;
                if(muriMattoni == 4 && muriCemento == 4)
                    muriCemento = 0;
            }else
            {
                while (muriMattoni < 4 && muriCemento == 4)
                    cemento.await();
                muriCemento++;
                if (muriMattoni == 4 && muriCemento == 4)
                    muriMattoni = 0;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
        return livelliCompletati < NUM_STRISCE;
    }

    @Override
    public void termina(int t)
    {
        System.out.println("Il muratore " + Thread.currentThread().getName() + " ha finito di mettere i" + (t == 0?" mattoni":"l cemento"));
    }
}
