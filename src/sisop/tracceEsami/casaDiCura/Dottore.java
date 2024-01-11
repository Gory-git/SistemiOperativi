package sisop.tracceEsami.casaDiCura;

import java.util.Random;

public class Dottore extends Thread
{
    private CasaDiCura casaDiCura;
    public Dottore(CasaDiCura casaDiCura)
    {
        this.casaDiCura = casaDiCura;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                System.out.println("---Il dottore sta iniziando prossimo intervento");
                casaDiCura.chiamaEIniziaOperazione();
                sleep(new Random().nextInt(20, 41)*100);
                casaDiCura.fineOperazione();
                System.out.println("---Il dottore sistema la sala operatoria per il prossimo intervento");
                sleep(200);
                System.out.println("---Il dottore ha sistemato la sala operatoria");
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
