package sisop.tracceEsami.visualizzatore;

public abstract class Coda
{
    protected String[] coda;
    protected int in;
    protected int out;

    public Coda(int dimensione)
    {
        this.coda = new String[dimensione];
        this.in = 0;
        this.out = 0;
    }
    protected abstract void put(String[] stringhe);
    protected abstract String get();
}
