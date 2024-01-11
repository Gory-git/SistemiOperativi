package sisop.tracceEsami1.aziendaAgricola1;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaSemafori extends AziendaAgricola
{
    private Semaphore ritira, magazziniere;

    public AziendaAgricolaSemafori(int quantita, int costo)
    {
        super(quantita, costo);
        this.ritira = new Semaphore(1, true);
        this.magazziniere = new Semaphore(0);
    }

    @Override
    public void ritira()
    {
        try
        {
            if(disponibilita == 0)
                magazziniere.release();

            ritira.acquire();
            this.disponibilita--;
            ritira.release();
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
            ritira.acquire();
            System.out.println("Magazziniere all'opera");
            this.disponibilita = this.quantita;
            ritira.release();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
