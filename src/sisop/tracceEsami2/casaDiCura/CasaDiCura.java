package sisop.tracceEsami2.casaDiCura;

public abstract class CasaDiCura
{
    protected final int POSTI_SALA_ATTESA;
    protected int contatoreAttesa = 0;
    protected CasaDiCura(int postiSalaAttesa)
    {
        POSTI_SALA_ATTESA = postiSalaAttesa;
    }

    public abstract void chiamaEIniziaOperazione() throws Exception;
    public abstract void fineOperazione() throws Exception;
    public abstract void pazienteEntra() throws Exception;
    public abstract void pazienteEsci() throws Exception;

}
