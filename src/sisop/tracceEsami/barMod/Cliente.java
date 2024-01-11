package sisop.tracceEsami.barMod;

import java.util.Random;

public class Cliente extends Thread
{
    BarMod bar;
    public Cliente(String nome, BarMod bar)
    {
        super(nome);
        this.bar = bar;
    }

    public void run()
    {
        int i = bar.scegli();
        bar.inizia(i);
        aspetta(i);
        bar.finisci(i);

        i = 1 - i;

        bar.inizia(i);
        aspetta(i);
        bar.finisci(i);
    }

    private void aspetta(int i)
    {
        int inf = 5 + i * 15;
        int sup = 11 + i * 30;
        int secondi = new Random().nextInt(inf, sup);
        try
        {
            sleep(secondi * 1_000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
