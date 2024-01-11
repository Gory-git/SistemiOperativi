package sisop.tracceEsami.aziendaAgricola;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaSemafori extends AziendaAgricola
{
    private Semaphore acquisto, magazziniere;

    public AziendaAgricolaSemafori(int numeroSacchi, int costoUnitario)
    {
        super(numeroSacchi, costoUnitario);
        acquisto = new Semaphore(1, true);
        magazziniere = new Semaphore(0, true);
    }

    @Override
    public void acquista(int numeroSacchiAcquistati)
    {
            try
            {
                acquisto.acquire();
                if(sacchiRimasti < numeroSacchiAcquistati)
                    magazziniere.release();

                System.out.println("Sono stati acquistati " + numeroSacchiAcquistati + " sacchi.");
                this.incassoGiornaliero += this.costoUnitario * numeroSacchiAcquistati;
                this.sacchiRimasti -= numeroSacchiAcquistati;
                System.out.println("Sono rimasti " + sacchiRimasti + " sacchi.");
                acquisto.release();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
    }

    @Override
    public void ripristina()
    {
        try
        {
            magazziniere.acquire();
            this.sacchiRimasti = this.numeroSacchi;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
