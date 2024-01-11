package sisop.tracceEsami2.bar;

public abstract class Bar
{
    protected final int[] DURATA = {1,2};
    protected final int[] PREZZO = {6,8};
    protected final int NUMERO_CLIENTI;

    protected Bar(int numeroClienti)
    {
        this.NUMERO_CLIENTI = numeroClienti;
    }


    public abstract void ordinaCocktail(int tipo) throws Exception;
    public abstract void preparaCocktail() throws Exception;
    public abstract void beviEPaga(int tipo) throws Exception;
    public abstract void chiudiBar() throws Exception;
}
