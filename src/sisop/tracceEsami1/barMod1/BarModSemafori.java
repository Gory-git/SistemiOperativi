package sisop.tracceEsami1.barMod1;

import java.util.concurrent.Semaphore;

public class BarModSemafori extends BarMod
{
    private Semaphore mutexBancone, mutexCassa, bancone, cassa;

    public BarModSemafori(int postiBancone)
    {
        super(postiBancone);
        this.mutexBancone = new Semaphore(1, true);
        this.mutexCassa = new Semaphore(1, true);
        this.bancone = new Semaphore(postiBancone, true);
        this.cassa = new Semaphore(1, true);
    }

    @Override
    public int scegli()
    {
        int codaB = 0;
        int codaC = 0;
        try
        {
            mutexBancone.acquire();
            codaB = codaBancone - permessiBanconeDisponibili;
            mutexBancone.release();
            mutexCassa.acquire();
            codaC = codaCassa - 1;
            mutexCassa.release();

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        if(codaB == codaC)
            return random.nextInt(0, 2);
        if(codaB > codaC)
            return 0;
        return 1;
    }

    @Override
    public void inizia(int scelta)
    {
        try
        {
            Thread corrente = Thread.currentThread();
            if(scelta == 0)
            {
                System.out.println(corrente.getName() +" si sta mettendo in coda al bancone.");
                bancone.acquire();
                mutexBancone.acquire();
                codaBancone++;
                mutexBancone.release();
            }else
            {
                System.out.println(corrente.getName() + " si sta mettendo in coda alla cassa.");
                cassa.acquire();
                mutexCassa.acquire();
                codaCassa++;
                mutexCassa.release();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void finisci(int scelta)
    {
        try
        {
            Thread corrente = Thread.currentThread();
            if(scelta == 0)
            {
                System.out.println(corrente.getName() + " ha terminato al bancone");
                mutexBancone.acquire();
                codaBancone--;
                mutexBancone.release();
                bancone.release();
            }else
            {
                System.out.println(corrente.getName() + " ha terminato alla cassa");
                mutexCassa.acquire();
                codaCassa--;
                mutexCassa.release();
                cassa.release();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
