package sisop.tracceEsami2.casello;

public abstract class Casello
{
    protected final int NUMERO_PORTE, TARIFFA;
    protected int incasso = 0;

    protected Casello(int numeroPorte, int tariffa)
    {
        NUMERO_PORTE = numeroPorte;
        TARIFFA = tariffa;
    }

    public abstract void accedi(int tratta, int porta) throws Exception;
}
