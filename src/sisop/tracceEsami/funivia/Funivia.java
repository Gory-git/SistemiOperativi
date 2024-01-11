package sisop.tracceEsami.funivia;

public abstract class Funivia
{
    protected final int PEDONE = 0;
    protected final int CICLISTA = 1;
    protected int postiLiberi, viaggio;

    public Funivia(int postiLiberi)
    {
        this.postiLiberi = postiLiberi;
        this.viaggio = 0;
    }

    public abstract void pilotaStart();
    public abstract void pilotaEnd();
    public abstract void turistaSali(int t);
    public abstract void turistaScendi(int t);
}
