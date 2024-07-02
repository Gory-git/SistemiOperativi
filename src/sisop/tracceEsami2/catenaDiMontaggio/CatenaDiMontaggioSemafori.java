package sisop.tracceEsami2.catenaDiMontaggio;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CatenaDiMontaggioSemafori extends CatenaDiMontaggio
{
    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] pezziDaProdurre = new Semaphore[]{new Semaphore(0), new Semaphore(0)};
    private Semaphore[] pezziProdotti = new Semaphore[]{new Semaphore(0), new Semaphore(0)};
    private Semaphore possoAssemblare = new Semaphore(0);
    private int[] numeroPezziDaProdurre = {0,0};
    private int[] numeroPezziProdotti = {0,0};

    @Override
    public void richiediProduzione(int pezziSx, int pezziDx) throws Exception
    {
        mutex.acquire();
        numeroPezziDaProdurre[0] += pezziSx;
        numeroPezziDaProdurre[1] += pezziDx;
        mutex.release();

        pezziDaProdurre[0].release(pezziSx);
        pezziDaProdurre[1].release(pezziDx);
    }

    @Override
    public void produci(int tipo) throws Exception
    {
        pezziDaProdurre[tipo].acquire();
        mutex.acquire();
        numeroPezziDaProdurre[tipo]--;
        numeroPezziProdotti[tipo]++;
        if (numeroPezziDaProdurre[tipo] == 0)
            possoAssemblare.release();
        mutex.release();
        pezziProdotti[tipo].release();
        System.out.println("Prodotto un pezzo " + (tipo == 0? "sinistro da " : "destro da ") + Thread.currentThread().getName());
    }

    @Override
    public void assembla() throws Exception
    {
        possoAssemblare.acquire(2);

        //mutex.acquire();
        while (numeroPezziProdotti[0] > 0 && numeroPezziProdotti[1] > 0)
        {
            numeroPezziProdotti[0]--;
            numeroPezziProdotti[1]--;
            pezziProdotti[0].acquire();
            pezziProdotti[1].acquire();
            TimeUnit.MILLISECONDS.sleep(20);
            System.out.println("Assemblati due pezzi!");
        }
        mutex.release();
    }
}