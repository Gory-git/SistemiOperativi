package sisop.tracceEsami.visualizzatore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaMonitor extends Coda
{
    private Lock scrivi;
    private int postiLiberi;

    private Condition cScrivi, cLeggi;
    public CodaMonitor(int dimensione)
    {
        super(dimensione);
        postiLiberi = dimensione;
        scrivi = new ReentrantLock();

        cScrivi = scrivi.newCondition();
        cLeggi = scrivi.newCondition();
    }

    @Override
    protected void put(String[] stringhe)
    {
        scrivi.lock();
        try
        {
            while(postiLiberi < stringhe.length)
            {
                System.out.println("---FULL---");
                cScrivi.await();
            }

            for(String stringa : stringhe)
            {
                System.out.println(">> " + stringa);
                this.coda[in] = stringa;
                in = (in + 1) % this.coda.length;
                postiLiberi -= 1;
                cLeggi.signal();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            scrivi.unlock();
        }

    }

    @Override
    protected String get()
    {
        String ret = "";
        scrivi.lock();
        try
        {
            while (coda.length == postiLiberi)
            {
                System.out.println("---EMPTY---");
                cLeggi.await();
            }
            ret = this.coda[out];
            out = (out + 1) % this.coda.length;
            postiLiberi += 1;
            cScrivi.signal();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            scrivi.unlock();
        }
        return ret;
    }
}
