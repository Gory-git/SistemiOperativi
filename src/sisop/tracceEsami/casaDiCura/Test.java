package sisop.tracceEsami.casaDiCura;

public class Test
{
    public static void main(String[] args)
    {
        int dim = 3;
        CasaDiCura casaDiCura = new CasaDiCuraMon(dim);
        Dottore dottore = new Dottore(casaDiCura);
        dottore.setDaemon(true);
        dottore.start();
        for (int i = 0; i < 10; i++)
        {
            new Paziente(("Paziente " + i), casaDiCura).start();
            try
            {
                Thread.sleep(2_000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
