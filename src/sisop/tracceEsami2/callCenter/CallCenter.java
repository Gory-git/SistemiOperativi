package sisop.tracceEsami2.callCenter;

import java.util.Random;

public abstract class CallCenter
{
    protected final int NUMERO_OPERATORI, NUMERO_CLIENTI;

    public CallCenter(int numeroOperatori, int numeroClienti)
    {
        this.NUMERO_OPERATORI = numeroOperatori;
        this.NUMERO_CLIENTI = numeroClienti;
    }
    public abstract void richiediAssistenza() throws Exception;
    public abstract void fornisciAssistenza() throws Exception;
    public abstract void terminaChiamata() throws Exception;
    public abstract void prossimoCliente() throws Exception;

}
