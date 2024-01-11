package sisop.threads.filosofi;

import java.util.concurrent.Semaphore;

public class TavoloSemafori extends Tavolo
{
    private Semaphore[] semaforiBacchette;

    public TavoloSemafori(int numeroFilosofi, int numeroBacchette)
    {
        super(numeroFilosofi, numeroBacchette);
        this.semaforiBacchette = new Semaphore[numeroBacchette];
        for (int i = 0; i < numeroBacchette; i++)
        {
            semaforiBacchette[i] = new Semaphore(1, true);
        }
    }

    @Override
    void prendiBacchetta(int ID) throws Exception
    {
        int idBacchettaDX = ID;
        int idBacchettaSX = (ID+1)%numeroBacchette;
        if(ID % 2 == 0)
        {
            semaforiBacchette[idBacchettaDX].acquire();
            semaforiBacchette[idBacchettaSX].acquire();
        }else
        {
            semaforiBacchette[idBacchettaSX].acquire();
            semaforiBacchette[idBacchettaDX].acquire();
        }
    }

    @Override
    void rilasciaBacchetta(int ID)
    {
        int idBacchettaDX = ID;
        int idBacchettaSX = (ID+1)%numeroBacchette;

        semaforiBacchette[idBacchettaDX].release();
        semaforiBacchette[idBacchettaSX].release();
    }
}
