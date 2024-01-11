package sisop.tracceEsami.bar;

public abstract class Bar
{

    protected int numeroClienti;

    public Bar(int numeroClienti)
    {
        this.numeroClienti = numeroClienti;
    }

    public abstract void ordinaCocktail(int tipo);
    public abstract void preparaCocktail();
    public abstract void beviEPaga(int tipo);
    public abstract void chiudiBar();

}
