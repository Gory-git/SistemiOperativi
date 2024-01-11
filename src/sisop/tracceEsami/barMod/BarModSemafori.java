package sisop.tracceEsami.barMod;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BarModSemafori extends BarMod
{
    private Semaphore bancone, cassa;
    public BarModSemafori(int lunghezzaBancone)
    {
        super(lunghezzaBancone);
        this.bancone = new Semaphore(lunghezzaBancone, true);
        this.cassa = new Semaphore(1,true);
    }

    @Override
    public int scegli()
    {
        if(bancone.getQueueLength() == cassa.getQueueLength())
            return new Random().nextInt(0,2);
        else if (bancone.availablePermits() > 0)
            return BANCONE;
        else
            return CASSA;
    }

    @Override
    public void inizia(int i)
    {
        if(i == CASSA)
            try
            {
                cassa.acquire();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        else
            try
            {
                bancone.acquire();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

    }

    @Override
    public void finisci(int i)
    {
        if(i == CASSA)
            try
            {
                cassa.release();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        else
            try
            {
                bancone.release();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
    }
}
