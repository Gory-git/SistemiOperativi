package sisop.tracceEsami.casello;

import java.util.Random;

public abstract class Casello
{
    protected int incassoTotale;
    protected static Random random = new Random();
    protected int numeroPorte, tariffa;

    public Casello(int numeroPorte, int tariffa)
    {
        this.incassoTotale = 0;
        this.numeroPorte = numeroPorte;
        this.tariffa = tariffa;
    }
    protected int getPorta()
    {
        return random.nextInt(0, numeroPorte);
    }
    public abstract void accedi(int chilometriPercorsi, String nome);
}
