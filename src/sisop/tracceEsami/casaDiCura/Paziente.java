package sisop.tracceEsami.casaDiCura;

public class Paziente extends Thread
{
    CasaDiCura casaDiCura;
    public Paziente(String nome, CasaDiCura casaDiCura)
    {
        super(nome);
        this.casaDiCura = casaDiCura;
    }

    @Override
    public void run()
    {
        System.out.println("+++" + getName() + " si sta svegliando.");
        casaDiCura.pazinteEntra();
        casaDiCura.pazinteEsci();
    }
}
