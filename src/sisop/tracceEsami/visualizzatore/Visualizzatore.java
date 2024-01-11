package sisop.tracceEsami.visualizzatore;

public class Visualizzatore extends Thread
{
    private Coda coda;

    public Visualizzatore(Coda coda)
    {
        this.coda = coda;
    }

    public void run()
    {
        while(true)
        {
            System.out.println("<< " + coda.get());
        }
    }
}
