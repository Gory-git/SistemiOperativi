package sisop.tracceEsami.casello;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CaselloSemafori extends Casello
{
    private Semaphore[] semafori;
    private Semaphore mutex;
    public CaselloSemafori(int numeroPorte, int tariffa)
    {
        super(numeroPorte, tariffa);
        semafori = new Semaphore[numeroPorte];
        for (int i = 0; i < numeroPorte; i++)
        {
            semafori[i] = new Semaphore(1, true);
        }
        this.mutex = new Semaphore(1, true);
    }

    @Override
    public void accedi(int chilometriPercorsi, String nome)
    {
        int i = getPorta();
        try
        {
            this.semafori[i].acquire();


            int costo = this.tariffa * chilometriPercorsi;

            this.mutex.acquire();
            this.incassoTotale += costo;
            this.mutex.release();

            System.out.println("L'automobile " + nome + " ha pagato " + costo + " euro alla porta " + i);

            Thread.sleep(random.nextInt(3, 7)*1_000);

            this.semafori[i].release();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
