package sisop.tracceEsami.casello;

import java.util.Random;

public class Automobile extends Thread
{
    private int chilometriPercorsi;
    private Casello casello;
    private static final Random RANDOM = new Random();

    public Automobile(Casello casello, String nome)
    {
        super(nome);
        this.casello = casello;
        this.chilometriPercorsi = RANDOM.nextInt(50, 100 + 1);
    }

    public void run()
    {
        try
        {
            System.out.println("L'automobile " + this.getName() + " è partita");
            sleep(chilometriPercorsi * 4_000);

            System.out.println("L'automobile " + this.getName() + " è arrivata al casello dopo aver percorso " + chilometriPercorsi + " chilometri");
            casello.accedi(chilometriPercorsi, this.getName());
            System.out.println("L'automobile " + this.getName() + " ha lasciato con successo l\'autostrada");

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
