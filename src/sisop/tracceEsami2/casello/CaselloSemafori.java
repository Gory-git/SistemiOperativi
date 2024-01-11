package sisop.tracceEsami2.casello;

import java.util.concurrent.Semaphore;

public class CaselloSemafori extends Casello
{
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore[] porte;
    protected CaselloSemafori(int numeroPorte, int tariffa)
    {
        super(numeroPorte, tariffa);
        this.porte = new Semaphore[numeroPorte];
        for (int i = 0; i < numeroPorte; i++)
        {
            porte[i] = new Semaphore(1, true);
        }
    }

    @Override
    public void accedi(int tratta, int porta) throws Exception
    {
        porte[porta].acquire();
        mutex.acquire();
        incasso += tratta * TARIFFA;
        porte[porta].release();
    }
}