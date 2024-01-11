package sisop.tracceEsami.barMod;

import java.util.Random;

public abstract class BarMod
{
    protected final int CASSA = 0;
    protected final int BANCONE = 1;
    protected int lunghezzaBancone, filaBancone, filaCassa;

    public BarMod(int lunghezzaBancone)
    {
        this.lunghezzaBancone = lunghezzaBancone;
        this.filaBancone = 0;
        this.filaCassa = 0;
    }

    public abstract int scegli();
    public abstract void inizia(int i);
    public abstract void finisci(int i);
}
