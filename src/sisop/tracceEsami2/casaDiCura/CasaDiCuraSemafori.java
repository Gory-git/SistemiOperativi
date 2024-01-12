package sisop.tracceEsami2.casaDiCura;

import java.security.PrivateKey;
import java.util.concurrent.Semaphore;

public class CasaDiCuraSemafori extends CasaDiCura
{
    private Semaphore ingresso = new Semaphore(1);
    private Semaphore attesa;
    private Semaphore entrare = new Semaphore(0);
    private Semaphore operare = new Semaphore(0);
    private Semaphore uscire = new Semaphore(0);
    protected CasaDiCuraSemafori(int postiSalaAttesa)
    {
        super(postiSalaAttesa);
        attesa = new Semaphore(postiSalaAttesa, true);
    }

    @Override
    public void chiamaEIniziaOperazione() throws Exception
    {
        entrare.release();
        operare.acquire();
    }

    @Override
    public void fineOperazione() throws Exception
    {
        uscire.release();
    }

    @Override
    public void pazienteEntra() throws Exception
    {
        ingresso.acquire();
        attesa.acquire();
        ingresso.release();
        entrare.acquire();
        operare.release();
        attesa.release();
    }

    @Override
    public void pazienteEsci() throws Exception
    {
        uscire.acquire();
    }
}
