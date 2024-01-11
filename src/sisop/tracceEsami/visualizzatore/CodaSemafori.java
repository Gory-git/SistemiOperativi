package sisop.tracceEsami.visualizzatore;

import sisop.tracceEsami.casello.Casello;

import java.util.concurrent.Semaphore;

public class CodaSemafori extends Coda
{
    private final Semaphore scrivi;
    private final Semaphore leggi = new Semaphore(0);
    private final Semaphore mutex = new Semaphore(1);

    public CodaSemafori(int dimensione)
    {
        super(dimensione);
        scrivi = new Semaphore(dimensione);
    }

    @Override
    protected void put(String[] stringhe)
    {
        int x = stringhe.length;
        try
        {
            scrivi.acquire(x);
            //mutex.acquire();
            for(String stringa : stringhe)
            {
                System.out.println(">> " + stringa);
                this.coda[in] = stringa;
                in = (in + 1) % this.coda.length;
            }
            leggi.release(x);
            //mutex.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected String get()
    {
        String ret = "";
        try
        {
            leggi.acquire();
            //mutex.acquire();
            ret = this.coda[out];
            out = (out + 1) % this.coda.length;
            scrivi.release();
            //mutex.release();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        return ret;
    }
}
