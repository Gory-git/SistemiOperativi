package sisop.tracceEsami.bar;

public class Barista extends Thread
{
    private Bar bar;
    public Barista(String nome, Bar bar)
    {
        super(nome);
        this.bar =bar;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println("Il barista sta aspettando di preparare tutti i cocktail");
            bar.preparaCocktail();
            System.out.println("Il barista ha preparato tutti i cocktail, aspetta che tutti escano per chiudere il bar");
            bar.chiudiBar();
            System.out.println("Il bar ha chiuso.");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
