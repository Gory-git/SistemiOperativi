package sisop.tracceEsami.casaDiCura;

import java.util.concurrent.Semaphore;

public class CasaDiCuraSem extends CasaDiCura
{
    private Semaphore salaPreparazione, salaOperatoria, possoOperare, possoUscire;

    public CasaDiCuraSem(int dimensioneSalaPreparazione)
    {
        super(dimensioneSalaPreparazione);
        this.salaPreparazione = new Semaphore(dimensioneSalaPreparazione);
        this.salaOperatoria = new Semaphore(0, true);
        this.possoOperare = new Semaphore(0);
        this.possoUscire = new Semaphore(0);

    }

    @Override
    public void chiamaEIniziaOperazione()
    {
        try
        {
            System.out.println("---Il medico chiama un paziente.");
            salaOperatoria.release();
            possoOperare.acquire();
            System.out.println("---Il medico inizia ad operarlo.");
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fineOperazione()
    {
        System.out.println("---Il medico ha terminato di operare il paziente.");
        possoUscire.release();
        System.out.println("---Il medico fa uscire il paziente dalla sala operatoria.");
    }

    @Override
    public void pazinteEntra()
    {
        System.out.println("+++" + Thread.currentThread().getName() + " arriva alla casa di cura e fa la fila per entrare.");
        try
        {
            salaPreparazione.acquire();
            System.out.println("+++" + Thread.currentThread().getName() + " entra in sala di preparazione e aspetta di essere chiamato dal chirurgo.");
            salaOperatoria.acquire();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        salaPreparazione.release();
        possoOperare.release();
        System.out.println("+++" + Thread.currentThread().getName() + " entra in sala operatoria.");
    }

    @Override
    public void pazinteEsci()
    {
        try
        {
            possoUscire.acquire();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("+++" + Thread.currentThread().getName() + " sta uscendo dalla sala operatoria.");
    }
}
