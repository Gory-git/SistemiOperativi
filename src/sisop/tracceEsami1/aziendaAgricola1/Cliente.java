package sisop.tracceEsami1.aziendaAgricola1;

import java.util.Random;

public class Cliente extends Thread
{
    private Random random;
    private AziendaAgricola aziendaAgricola;

    public int quantita;
    public Cliente(String nome, AziendaAgricola aziendaAgricola)
    {
        super(nome);
        this.aziendaAgricola = aziendaAgricola;
        random = new Random();
        quantita = random.nextInt(1, 11);
    }

    public void run()
    {
        int spesa = aziendaAgricola.acquista(quantita);
        System.out.println("Cliente " + getName() + " ha speso " + spesa + " per " + quantita + " sacchi.");
        while(quantita > 0)
        {
            aziendaAgricola.ritira();
            quantita--;
            System.out.println("Cliente " + getName() + " ha ritirato un sacco, gliene mancano " + quantita);
            try
            {
                sleep(1_000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Cliente " + getName() + " ha ritirato tutti i suoi sacchi.");
    }



}
