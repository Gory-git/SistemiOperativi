package sisop.tracceEsami1.barMod1;

import java.util.Random;

public abstract class BarMod
{
    protected int permessiBanconeDisponibili, codaBancone, codaCassa;
    protected Random random;
    public BarMod(int postiBancone)
    {
        this.permessiBanconeDisponibili = postiBancone;
        this.codaBancone = 0;
        this.codaCassa = 0;
        random = new Random();
    }
    public abstract int scegli();// BANCONE = 0, CASSA = 1

    public abstract void inizia(int scelta);
    public abstract void finisci(int scelta);
}
