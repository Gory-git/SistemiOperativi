package sisop.tracceEsami2.barMod;

import java.util.LinkedList;

public abstract class BarMod
{
    protected final int POSTI_BANCONE = 5;
    protected int[] contatori = {0,0};

    public abstract int scegli() throws Exception;
    public abstract void inizia(int scelta) throws Exception;
    public abstract void finisci(int scelta) throws Exception;
}
