package sisop.threads.esercizi4;

import java.util.concurrent.Semaphore;

public class Stampante extends Thread
{
    private String name;
    private static Semaphore semaphoreA = new Semaphore(2, true);
    private static Semaphore semaphoreB = new Semaphore(0, true);

    public Stampante(String name)
    {
        super(name);
    }

    @Override
    public void run()
    {
        try {
            if(this.getName().equals("A"))
            {
                semaphoreA.acquire();
                System.out.print(this.getName());
                semaphoreB.release();
            }else
            {
                semaphoreB.acquire(2);
                System.out.println(this.getName());
                semaphoreA.release(2);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
