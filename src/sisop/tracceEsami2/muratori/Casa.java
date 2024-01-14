package sisop.tracceEsami2.muratori;

import java.security.PublicKey;

public abstract class Casa
{
    protected final int NUM_STRATI;
    protected int[] stratiFiniti = new int[4];
    protected int[] muroAttuale = new int[2];
    public Casa(int numStrati)
    {
        NUM_STRATI = numStrati;
    }

    public abstract void inizia(int tipo) throws Exception;
    public abstract void finisci(int tipo) throws Exception;
}
