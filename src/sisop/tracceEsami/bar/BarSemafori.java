package sisop.tracceEsami.bar;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class BarSemafori extends Bar
{
    private Semaphore possoPreparare;
    private Semaphore possoChiudere;
    private Semaphore cassa = new Semaphore(1, true);
    private Semaphore mutex = new Semaphore(1);

    private HashMap<Thread, Integer> ordini = new HashMap<>();
    private Semaphore[] attesa;


    public BarSemafori(int numeroClienti)
    {
        super(numeroClienti);
        possoPreparare = new Semaphore(-numeroClienti);
        possoChiudere = new Semaphore(-numeroClienti);
        attesa = new Semaphore[numeroClienti];
        for (int i = 0; i < numeroClienti; i++)
        {
            attesa[i] = new Semaphore(0);
        }
    }

    @Override
    public void ordinaCocktail(int tipo)
    {
        possoPreparare.release();
        try
        {
            mutex.acquire();
            ordini.put(Thread.currentThread(), tipo);
            mutex.release();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void preparaCocktail()
    {
        try
        {
            possoPreparare.acquire();
            mutex.acquire();
            for(Thread k : ordini.keySet())
            {
                int tempo = ordini.get(k) + 1;

                attesa[Integer.parseInt(k.getName())].release();

                System.out.println("Il barista sta preparando un cocktail " + (tempo == 1? "normale":"speciale") + " al cliente " + k.getName());
                Thread.sleep(tempo * 1000);
                System.out.println("Il barista ha preparato il cocktail " + (tempo == 1? "normale":"speciale") + " al cliente " + k.getName());

            }
            mutex.release();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        possoChiudere.release();
    }

    @Override
    public void beviEPaga(int tipo)
    {
        try
        {
            mutex.acquire();
            if(ordini.size() == numeroClienti)
                possoPreparare.release();
            mutex.release();
            attesa[Integer.parseInt(Thread.currentThread().getName())].acquire();
            cassa.acquire();
            System.out.println("Il cliente " + Thread.currentThread().getName() + " sta pagando alla cassa");
            cassa.release();
            possoChiudere.release();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void chiudiBar()
    {
        try
        {
            possoChiudere.acquire();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
