package sisop.threads.saluti;

public class SalutiR implements Runnable
{
    private String nome;

    public SalutiR (String nome)
    {
        this.nome = nome;
    }

    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Ciao da " + nome);
        }
    }
}

class SalutiRTest
{
    public static void main(String[] args)
    {
        SalutiR r = new SalutiR("Secondo thread");
        Thread t = new Thread(r);

        t.start();
    }
}