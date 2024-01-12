package sisop.tracceEsami2.barMod;

import java.util.concurrent.Semaphore;

public class BarModSemafori extends BarMod
{
    //SCELTA: 0 = CASSA, 1 = BANCONE;
    private Semaphore[] semafori = new Semaphore[]{new Semaphore(1, true), new Semaphore(POSTI_BANCONE, true)};
    private Semaphore mutex = new Semaphore(1);

    @Override
    public int scegli() throws Exception
    {
        mutex.acquire();
        int x = contatori[0];
        int y = contatori[1]-5;
        mutex.release();
        return x < y ? 0 : 1;
    }

    @Override
    public void inizia(int scelta) throws Exception
    {
        mutex.acquire();
        contatori[scelta]++;
        mutex.release();
        semafori[scelta].acquire();
    }

    @Override
    public void finisci(int scelta) throws Exception
    {
        mutex.acquire();
        contatori[scelta]--;
        mutex.release();
        semafori[scelta].release();
    }
}
