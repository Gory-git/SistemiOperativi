package sisop.threads.esercizi5.produttoreConsumatore;

import java.util.Random;

public class Produttore extends Thread
{
    private Buffer buffer;
    private Random r;
    public Produttore(Buffer buffer)
    {
        this.buffer = buffer;
        r = new Random();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                //Thread.sleep(1000);
                int prodotto = r.nextInt(1_000);
                buffer.put(prodotto);
                System.out.println("Ho prodotto " + prodotto);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
