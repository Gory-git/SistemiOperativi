package sisop.threads.produttoriConsumatori;

public abstract class Buffer
{
    protected int disponibilita, limite;
    protected int[] buffer;

    public Buffer(int disponibilita)
    {
        this.disponibilita = disponibilita;
        this.limite = disponibilita;
        this.buffer = new int[disponibilita];
        for (int i = 0; i < disponibilita; i++)
        {
            this.buffer[i] = 0;
        }
    }

    public abstract void put(int value) throws Exception;
    public abstract int get() throws Exception;
}
