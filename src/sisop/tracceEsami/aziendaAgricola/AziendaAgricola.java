package sisop.tracceEsami.aziendaAgricola;

public abstract class AziendaAgricola
{
    protected int numeroSacchi, sacchiRimasti, costoUnitario, incassoGiornaliero;

    public AziendaAgricola(int numeroSacchi, int costoUnitario)
    {
        this.numeroSacchi = numeroSacchi;
        this.sacchiRimasti = numeroSacchi;
        this.costoUnitario = costoUnitario;
        this.incassoGiornaliero = 0;
    }

    public abstract void acquista(int numeroSacchiAcquistati);
    public abstract void ripristina();

    public int getIncassoGiornaliero() {
        return incassoGiornaliero;
    }
}
