package sisop.tracceEsami1.barMod1;

import java.util.Random;

public class Cliente extends Thread
{
    private BarMod barMod;
    private Random random;
    public Cliente (String nome, BarMod barMod)
    {
        super(nome);
        this.barMod = barMod;
        this.random = new Random();
    }

    @Override
    public void run()
    {
        int scelta = barMod.scegli();

        barMod.inizia(scelta);
        int tempoMin = 5 + (scelta * 15);
        int tempoMAX = 10 + (scelta * 30) + 1;
        int tempo = random.nextInt(tempoMin, tempoMAX);
        try
        {
            sleep(tempo * 100);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        barMod.finisci(scelta);

        scelta = 1 - scelta;


        barMod.inizia(scelta);
        tempoMin = 5 + (scelta * 15);
        tempoMAX = 10 + (scelta * 30) + 1;
        tempo = random.nextInt(tempoMin, tempoMAX);
        try
        {
            sleep(tempo * 100);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        barMod.finisci(scelta);
    }
}
