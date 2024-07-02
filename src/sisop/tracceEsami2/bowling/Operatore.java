package sisop.tracceEsami2.bowling;

public class Operatore extends Thread
{
    private Bowling bowling;
    public Operatore( String nome, Bowling bowling)
    {
        super(nome);
        this.bowling = bowling;
    }

    @Override
    public void run()
    {
        try
        {
            bowling.preparaPartita();
            bowling.deposita();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
