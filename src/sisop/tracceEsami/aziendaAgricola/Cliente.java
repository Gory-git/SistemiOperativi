package sisop.tracceEsami.aziendaAgricola;

import java.util.Random;

public class Cliente extends Thread
{
    private Random random;
    private AziendaAgricola aziendaAgricola;

    public Cliente(String nome, AziendaAgricola aziendaAgricola)
    {
        super(nome);
        this.random = new Random();
        this.aziendaAgricola = aziendaAgricola;
    }

    public void run()
    {
        int numeroSacchi = random.nextInt(1, 11);
        System.out.println(getName() + " vuole comprare " + numeroSacchi + " sacchi.");
        aziendaAgricola.acquista(numeroSacchi);
        try
        {
            sleep(/*60_000*/ 1000* numeroSacchi);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(getName() + " ha lasciato l'azienda agricola");
    }
}
