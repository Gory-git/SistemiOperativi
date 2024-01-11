package sisop.tracceEsami1.aziendaAgricola1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaMonitor extends AziendaAgricola
{
    private Lock magazzino;
    private Condition magazzinoVuoto, magazziniere;
    public int sacchiPrelevati;
    public AziendaAgricolaMonitor(int quantita, int costo)
    {
        super(quantita, costo);

        this.magazzino = new ReentrantLock();

        this.magazzinoVuoto = magazzino.newCondition();
        this.magazziniere = magazzino.newCondition();

        this.sacchiPrelevati = 0;
    }

    @Override
    public void ritira()
    {
        magazzino.lock();
        try
        {
            while(disponibilita == 0)
                magazzinoVuoto.await();

            disponibilita--;
            sacchiPrelevati++;
            if (disponibilita == 0)
                magazziniere.signal();
            System.out.println("--- NUM SACCHI: " + disponibilita + " ---");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            magazzino.unlock();
        }
    }

    @Override
    public void ripristina()
    {
        System.out.println("Magazziniere all'opera");
        magazzino.lock();
        try
        {
            while(disponibilita > 0)
                magazziniere.await();
            disponibilita = quantita;
            magazzinoVuoto.signalAll();
        }catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            magazzino.unlock();
        }
    }
}
