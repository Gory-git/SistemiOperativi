package sisop.tracceEsami.casello;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaselloMonitor extends Casello
{
    private Lock mutex;
    private Lock[] porte;
    public CaselloMonitor(int numeroPorte, int tariffa)
    {
        super(numeroPorte, tariffa);
        mutex = new ReentrantLock();
        porte = new Lock[numeroPorte];
        for (int i = 0; i < numeroPorte; i++)
        {
            porte[i] = new ReentrantLock();
        }
    }

    @Override
    public void accedi(int chilometriPercorsi, String nome)
    {
        int i = getPorta();
        this.porte[i].lock();
        try
        {
            int costo = this.tariffa * chilometriPercorsi;

            this.mutex.lock();
            try
            {
                this.incassoTotale += costo;
            }finally
            {
                this.mutex.unlock();
            }
            System.out.println("L'automobile " + nome + " ha pagato " + costo + " euro alla porta " + i);

            Thread.sleep(random.nextInt(3, 7)*1_000);

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            this.porte[i].unlock();
        }
    }
}
