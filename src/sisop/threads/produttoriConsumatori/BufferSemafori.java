package sisop.threads.produttoriConsumatori;

import java.util.concurrent.Semaphore;

public class BufferSemafori extends Buffer
{
    private Semaphore disponibili;
    private Semaphore occupati = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public BufferSemafori(int disponibilita)
    {
        super(disponibilita);
        this.disponibili = new Semaphore(disponibilita);
    }

    @Override
    public void put(int value) throws Exception
    {
        disponibili.acquire();
        mutex.acquire();
        buffer[limite-disponibilita] = value;
        disponibilita--;
        mutex.release();
        occupati.release();
    }

    @Override
    public int get() throws Exception
    {
        int ret = 0;
        occupati.acquire();
        mutex.acquire();
        disponibilita++;
        ret = buffer[limite - disponibilita];
        mutex.release();
        disponibili.release();
        return ret;
    }
}
