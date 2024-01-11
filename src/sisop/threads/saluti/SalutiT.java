package sisop.threads.saluti;

public class SalutiT extends Thread
{
    public SalutiT (String nome)
    {
        super(nome);
    }

    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println("ciao da " + getName());
        }
    }
}
class SalutiTTest
{
    public static void main(String[] args)
    {
        SalutiT t = new SalutiT("primo thread");
        t.start();
    }
}
