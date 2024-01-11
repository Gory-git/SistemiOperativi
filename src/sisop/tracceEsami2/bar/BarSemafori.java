package sisop.tracceEsami2.bar;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BarSemafori extends Bar
{
    private LinkedList<Integer> listaOrdini = new LinkedList<>();
    private int incasso = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore possoPreparare = new Semaphore(0);
    private Semaphore possoBere = new Semaphore(0);
    private Semaphore cassa = new Semaphore(1, true);
    private Semaphore possoChiudere = new Semaphore(0);

    protected BarSemafori(int numeroClienti)
    {
        super(numeroClienti);
    }

    @Override
    public void ordinaCocktail(int tipo) throws Exception
    {
        mutex.acquire();
        listaOrdini.add(tipo);
        System.out.println(Thread.currentThread().getName() + " ha ordinato");
        mutex.release();
        if(listaOrdini.size() == NUMERO_CLIENTI)
            possoPreparare.release();
    }

    @Override
    public void preparaCocktail() throws Exception
    {
        possoPreparare.acquire();
        mutex.acquire();
        for(int cocktail : listaOrdini)
            TimeUnit.MINUTES.sleep(DURATA[cocktail]);
        mutex.release();
        possoBere.release(NUMERO_CLIENTI);
    }

    @Override
    public void beviEPaga(int tipo) throws Exception
    {
        possoBere.acquire();
        System.out.println(Thread.currentThread().getName() + " ha consumato");
        cassa.acquire();
        System.out.println(Thread.currentThread().getName() + " ha pagato " + PREZZO[tipo]);
        mutex.acquire();
        listaOrdini.remove();
        incasso += PREZZO[tipo];
        mutex.release();
        cassa.release();
        if (listaOrdini.isEmpty())
            possoChiudere.release();
    }

    @Override
    public void chiudiBar() throws Exception
    {
        possoChiudere.acquire();
        System.out.println("Il bar ha chiuso. L'incasso odierno: " + incasso);
    }
}
