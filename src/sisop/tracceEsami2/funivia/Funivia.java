package sisop.tracceEsami2.funivia;

import java.util.LinkedList;

public abstract class Funivia
{
    protected int turno = 0;
    protected final int POSTI_FUNIVIA = 6;
    protected int postiDisponibili = 6;
    protected LinkedList<Thread> funivia = new LinkedList<>();

    public abstract void pilotaStart() throws Exception;
    public abstract void pilotaEnd() throws Exception;
    public abstract void turistaSali(int tipo) throws Exception;
    public abstract void turistaScendi(int tipo) throws Exception;
}
