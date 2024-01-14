package sisop.tracceEsami2.muratori;

import java.util.concurrent.Semaphore;

public class CasaSemafori extends Casa  //tipo: 0 = cemento; 1 = mattoni;
{
    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] semafori = new Semaphore[]{new Semaphore(4, true), new Semaphore(0, true)};

    public CasaSemafori(int numStrati)
    {
        super(numStrati);
    }

    @Override
    public void inizia(int tipo) throws Exception
    {
        semafori[tipo].acquire();
        mutex.acquire();
        stratiFiniti[muroAttuale[tipo]%4] += tipo;
        mutex.release();
    }

    @Override
    public void finisci(int tipo) throws Exception
    {
        mutex.acquire();
        muroAttuale[tipo] += 1;
        mutex.release();
        semafori[1 - tipo].release();
    }
}
