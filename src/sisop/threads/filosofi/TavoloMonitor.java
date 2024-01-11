package sisop.threads.filosofi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TavoloMonitor extends Tavolo
{
    private Lock monitor = new ReentrantLock();
    private Condition[] bacchette;
    private boolean[] disponibili;
    public TavoloMonitor(int numeroFilosofi, int numeroBacchette)
    {
        super(numeroFilosofi, numeroBacchette);
        this.bacchette = new Condition[numeroBacchette];
        this.disponibili = new boolean[numeroBacchette];
        for (int i = 0; i < numeroBacchette; i++)
        {
            this.bacchette[i] = monitor.newCondition();
            this.disponibili[i] = true;
        }
    }

    @Override
    void prendiBacchetta(int ID) throws Exception
    {
        monitor.lock();
        try
        {
            int idBacchettaDX = ID;
            int idBacchettaSX = (ID + 1) % numeroBacchette;
            if (ID % 2 == 0)
            {
                while (!disponibili[idBacchettaDX])
                    bacchette[idBacchettaDX].await();
                disponibili[idBacchettaDX] = false;
                while (!disponibili[idBacchettaSX])
                    bacchette[idBacchettaSX].await();
                disponibili[idBacchettaSX] = false;
            } else
            {
                while (!disponibili[idBacchettaSX])
                    bacchette[idBacchettaSX].await();
                disponibili[idBacchettaSX] = false;
                while (!disponibili[idBacchettaDX])
                    bacchette[idBacchettaDX].await();
                disponibili[idBacchettaDX] = false;
            }
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    void rilasciaBacchetta(int ID)
    {
        monitor.lock();
        try
        {
            int idBacchettaDX = ID;
            int idBacchettaSX = (ID + 1) % numeroBacchette;
            bacchette[idBacchettaDX].signal();
            disponibili[idBacchettaDX] = true;
            bacchette[idBacchettaSX].signal();
            disponibili[idBacchettaSX] = true;
        }finally
        {
            monitor.unlock();
        }
    }
}
