package sisop.tracceEsami.bar;

import java.util.Random;

public class Cliente extends Thread
{
    private Bar bar;
    public Cliente (String nome, Bar bar)
    {
        super(nome);
        this.bar = bar;
    }

    @Override
    public void run()
    {
        try
        {
            int tipo = new Random().nextInt(0, 2);
            System.out.println("Il cliente " + getName() + " ordina il suo drink " + (tipo == 0?"normale":"speciale"));
            bar.ordinaCocktail(tipo);
            System.out.println("Il cliente " + getName() + " aspetta ch il suo drink sia pronto");
            bar.beviEPaga(tipo);
            System.out.println("Il cliente "+ getName()+" ha pagato e sta uscendo dal bar");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
