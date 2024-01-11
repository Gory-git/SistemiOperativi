package sisop.tracceEsami1.aziendaAgricola1;

public abstract class AziendaAgricola
{
    protected int quantita, disponibilita, costo, incasso;

    public AziendaAgricola(int quantita, int costo)
    {
        this.quantita = quantita;
        this.disponibilita = quantita;
        this.costo = costo;
        this.incasso = 0;
    }

    public int acquista(int quantita)
    {
        incasso += quantita * costo;
        return quantita * costo;
    }
    public abstract void ritira();
    public abstract void ripristina();
}
