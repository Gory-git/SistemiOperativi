package sisop.tracceEsami2.aziendaAgricola;

public abstract class AziendaAgricola
{
    protected final int PREZZO = 3;
    protected final int NUMERO_SACCHI = 200;
    protected int sacchi = 200;
    protected int incasso = 0;

    public abstract void paga(int numeroSacchi) throws Exception;
    public abstract void prelevaSacco() throws Exception;
    public abstract void rifornisciMagazzino() throws Exception;

}
