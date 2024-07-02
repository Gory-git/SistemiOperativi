package sisop.tracceEsami2.bowling;

public class Giocatore extends Thread
{
    private Bowling bowling;
    private final int NUMERO_DI_SCARPE;

    public Giocatore(String nome, Bowling bowling, int numeroDiScarpe)
    {
        super(nome);
        this.bowling =bowling;
        NUMERO_DI_SCARPE = numeroDiScarpe;
        this.setName(nome + "-" + numeroDiScarpe);
    }

    @Override
    public void run()
    {
        try
        {
            bowling.fornisciInformazioni();
            bowling.gioca(this.getName());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
