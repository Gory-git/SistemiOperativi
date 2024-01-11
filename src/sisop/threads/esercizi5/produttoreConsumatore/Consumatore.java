package sisop.threads.esercizi5.produttoreConsumatore;

import java.util.Random;

public class Consumatore extends Thread
{
    private Buffer buffer;
    public Consumatore(Buffer buffer)
    {
        this.buffer = buffer;
    }

    public void run()
    {
        try
        {
            while (true)
            {
                //Thread.sleep(1000);
                System.out.println("Ho consumato " + buffer.get());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
