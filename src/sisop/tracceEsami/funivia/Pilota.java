package sisop.tracceEsami.funivia;

public class Pilota extends Thread
{
    private Funivia funivia;
    public Pilota(Funivia funivia)
    {
        this.funivia = funivia;
    }

    @Override
    public void run()
    {
        while (true)
        {
            funivia.pilotaStart();
            try
            {
                sleep(10_000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            funivia.pilotaEnd();
        }
    }
}
