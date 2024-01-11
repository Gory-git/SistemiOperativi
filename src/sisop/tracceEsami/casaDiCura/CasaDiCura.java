package sisop.tracceEsami.casaDiCura;

public abstract class CasaDiCura
{

    protected int dimensioneSalaPreparazione;
    public CasaDiCura(int dimensioneSalaPreparazione)
    {
        this.dimensioneSalaPreparazione = dimensioneSalaPreparazione;
    }

    public abstract void chiamaEIniziaOperazione();
    public abstract void fineOperazione();
    public abstract void pazinteEntra();
    public abstract void pazinteEsci();
}
