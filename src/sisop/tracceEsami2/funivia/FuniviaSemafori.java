package sisop.tracceEsami2.funivia;

import java.lang.invoke.SwitchPoint;
import java.util.concurrent.Semaphore;

public class FuniviaSemafori extends Funivia
{
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore possoPartire = new Semaphore(0);
    private Semaphore[] possoSalire = new Semaphore[]{new Semaphore(0), new Semaphore(0)};
    private Semaphore possoScendere = new Semaphore(0);
    @Override
    public void pilotaStart() throws Exception
    {
        turno = 1 - turno;
        possoSalire[turno].release(POSTI_FUNIVIA);
        possoPartire.acquire();
    }

    @Override
    public void pilotaEnd() throws Exception
    {
        mutex.acquire();
        for (Thread passeggero : funivia)
            System.out.println("Scende il passeggero " + passeggero.getName());
        funivia.clear();
        mutex.release();
        possoScendere.release(POSTI_FUNIVIA);
    }

    @Override
    public void turistaSali(int tipo) throws Exception
    {
        int quanti = tipo + 1;
        possoSalire[tipo].acquire(quanti);
        mutex.acquire();
        postiDisponibili-=quanti;
        funivia.add(Thread.currentThread());
        mutex.release();
        if(postiDisponibili == 0)
            possoPartire.release();
    }

    @Override
    public void turistaScendi(int tipo) throws Exception
    {
        int quanti = tipo + 1;
        possoScendere.acquire(quanti);
        mutex.acquire();
        postiDisponibili += quanti;
        mutex.release();
    }
}
