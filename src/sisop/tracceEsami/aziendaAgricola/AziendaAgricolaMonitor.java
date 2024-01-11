package sisop.tracceEsami.aziendaAgricola;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaMonitor extends AziendaAgricola
{
    private final Lock monitor;
    private final Condition acquisti, magazziniere;

    public AziendaAgricolaMonitor(int numeroSacchi, int costoUnitario)
    {
        super(numeroSacchi, costoUnitario);

        monitor = new ReentrantLock(true);

        acquisti = monitor.newCondition();
        magazziniere = monitor.newCondition();
    }

    @Override
    public void acquista(int numeroSacchiAcquistati)
    {

        monitor.lock();
        try
        {
            while (sacchiRimasti < numeroSacchiAcquistati)
            {
                magazziniere.signal();
                acquisti.await();
            }
            System.out.println("Sono stati acquistati " + numeroSacchiAcquistati + " sacchi.");
            this.sacchiRimasti -= numeroSacchiAcquistati;
            this.incassoGiornaliero += this.costoUnitario * numeroSacchiAcquistati;
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void ripristina()
    {
        monitor.lock();
        try {
            while (sacchiRimasti > 0)
                magazziniere.await();
            sacchiRimasti = numeroSacchi;
            acquisti.signal();
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }

    }
}
