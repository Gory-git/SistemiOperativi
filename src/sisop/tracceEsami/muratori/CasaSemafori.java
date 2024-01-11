package sisop.tracceEsami.muratori;

import java.util.concurrent.Semaphore;

public class CasaSemafori extends Casa
{

    private Semaphore cemento = new Semaphore(0);
    private Semaphore mattoni = new Semaphore(4);
    private Semaphore mutex = new Semaphore(1);

    public CasaSemafori(int NUN_STRISCE, int NUM_PARETI)
    {
        super(NUN_STRISCE, NUM_PARETI);
    }

    @Override
    public boolean inizia(int t)
    {
        if(livelliCompletati < NUM_PARETI)
        {
            if (t == 0)
            {
                try
                {
                    mattoni.acquire();
                    muriMattoni++;
                    if(muriMattoni == NUM_PARETI)
                    {
                        cemento.release(4);
                        muriMattoni = 0;
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            } else
            {
                try
                {
                    cemento.acquire();
                    muriCemento++;
                    if(muriCemento == NUM_PARETI)
                    {
                        mattoni.release(4);
                        muriCemento = 0;
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public void termina(int t)
    {
        System.out.println("Il muratore " + Thread.currentThread().getName() + " ha finito di mettere i" + (t == 0?" mattoni":"l cemento"));
    }
}
