package sisop.tracceEsami.callCenter;

public abstract class CallCenter
{
    protected int numeroClienti, numeroOperatori;
    public CallCenter(int numeroClienti, int numeroOperatori)
    {
        this.numeroClienti = numeroClienti;
        this.numeroOperatori = numeroOperatori;
    }

    public abstract void richiediAssistenza();
    public abstract void fornisciAssistenza();
    public abstract void terminaChiamata();
    public abstract void prossimoCliente();

}
