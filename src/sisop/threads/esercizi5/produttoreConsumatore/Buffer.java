package sisop.threads.esercizi5.produttoreConsumatore;

public abstract class Buffer
{
    protected int[] buffer;
    protected int in;
    protected int out;

    public Buffer(int dimensione)
    {
        buffer = new int[dimensione];
        in = 0;
        out = 0;
    }

    public abstract void put(int i) throws InterruptedException;
    public abstract int get() throws InterruptedException;


}
