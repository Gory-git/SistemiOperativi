package sisop.tracceEsami1.aziendaAgricola1;

import java.util.Random;

public class Magazziniere extends Thread
{
    private Random random;
    private AziendaAgricola aziendaAgricola;

    public Magazziniere(AziendaAgricola aziendaAgricola)
    {
        random = new Random();
        this.aziendaAgricola = aziendaAgricola;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                aziendaAgricola.ripristina();
                sleep(10_000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            System.out.println("Magazzino rifornito");
        }
    }
}
