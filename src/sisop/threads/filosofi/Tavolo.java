package sisop.threads.filosofi;

public abstract class Tavolo
{
    protected int numeroFilosofi, numeroBacchette;

    public Tavolo(int numeroFilosofi, int numeroBacchette)
    {
        this.numeroFilosofi = numeroFilosofi;
        this.numeroBacchette = numeroBacchette;
    }
    abstract void prendiBacchetta(int ID) throws Exception;
    abstract void rilasciaBacchetta(int ID);
}
