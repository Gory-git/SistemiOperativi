package sisop.tracceEsami.muratori;

public abstract class Casa
{
    protected final int NUM_STRISCE, NUM_PARETI;
    protected int livelliCompletati, muriCemento, muriMattoni;
    public Casa(int NUN_STRISCE, int NUM_PARETI)
    {
        this.NUM_STRISCE = NUN_STRISCE;
        this.NUM_PARETI = NUM_PARETI;
        this.livelliCompletati = 0;
        this.muriCemento = 0;
        this.muriMattoni = 0;
    }
    /*
    * Sospende il muratore fino a quando non risulta disponibile il lavoro di tipo t
    * Restituisce false se non ci sono lavori da efettuare, true altrimenti
    */
    public abstract boolean inizia(int t);

    /*
    * permette al muratore di counicare che ha terminato il proprio lavoro
    */
    public abstract void termina(int t);
}
