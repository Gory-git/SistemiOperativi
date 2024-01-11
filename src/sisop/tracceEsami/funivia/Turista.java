package sisop.tracceEsami.funivia;

import java.util.Random;

public class Turista extends Thread
{
    private int ID;
    private Random random = new Random();
    private Funivia funivia;

    public Turista(String nome, int ID, Funivia funivia)
    {
        super(nome);
        this.ID = ID;
        this.funivia = funivia;
    }

    @Override
    public void run()
    {
        System.out.println(getName() + " è nato");
        funivia.turistaSali(ID);
        funivia.turistaScendi(ID);
    }
}
