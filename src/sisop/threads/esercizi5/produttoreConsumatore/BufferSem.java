package sisop.threads.esercizi5.produttoreConsumatore;

import java.util.concurrent.Semaphore;

public class BufferSem extends Buffer
{
    private Semaphore elementiDisponibili, postiDisponibili, mutex;

    public BufferSem(int dimensione)
    {
        super(dimensione);
        elementiDisponibili = new Semaphore(0);
        postiDisponibili = new Semaphore(dimensione);
        mutex = new Semaphore(1);
    }


    @Override
    public void put(int i) throws InterruptedException
    {
        postiDisponibili.acquire();
        mutex.acquire();
        buffer[in] = i;
        in = (in + 1) % buffer.length;
        mutex.release();
        elementiDisponibili.release();
    }

    @Override
    public int get() throws InterruptedException
    {
        elementiDisponibili.acquire();
        mutex.acquire();
        int ret = buffer[out];
        out = (out + 1) %  buffer.length;
        mutex.release();
        postiDisponibili.release();
        return ret;
    }
}
