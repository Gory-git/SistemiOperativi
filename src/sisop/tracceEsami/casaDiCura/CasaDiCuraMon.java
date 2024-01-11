package sisop.tracceEsami.casaDiCura;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaDiCuraMon extends CasaDiCura
{
    private Lock monitor = new ReentrantLock();
    private Condition codaIngresso = monitor.newCondition();
    private Condition attesaPreparazione = monitor.newCondition();
    private LinkedList<Thread> salaPreparazione = new LinkedList<>();
    private boolean possoEntrare = false;
    private Thread[] paziente = {null};
    private Condition operare = monitor.newCondition();
    private boolean possoOperare = false;
    private Condition uscire = monitor.newCondition();
    private boolean possoUscire = false;
    private int postiSala;
    public CasaDiCuraMon(int dimensioneSalaPreparazione)
    {
        super(dimensioneSalaPreparazione);
        this.postiSala = dimensioneSalaPreparazione;
    }

    @Override
    public void chiamaEIniziaOperazione()
    {
        monitor.lock();
        try
        {
            possoEntrare = true;
            System.out.println("---Il medico chiama un paziente.");
            possoUscire = false;
            attesaPreparazione.signal();
            while (!possoOperare)
                operare.await();
            possoOperare = false;
            System.out.println("---Il medico inizia ad operarlo.");

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            monitor.unlock();
        }

    }

    @Override
    public void fineOperazione()
    {
        monitor.lock();
        try
        {
            System.out.println("---Il medico ha terminato di operare il paziente.");
            possoUscire = true;
            uscire.signal();
            System.out.println("---Il medico fa uscire il paziente dalla sala operatoria.");
        }finally
        {
            monitor.unlock();
        }

    }

    @Override
    public void pazinteEntra()
    {
        monitor.lock();
        try
        {
            Thread corrente = Thread.currentThread();
            System.out.println("+++" + corrente.getName() + " arriva alla casa di cura e fa la fila per entrare.");
            while(postiSala == 0)
                codaIngresso.await();
            salaPreparazione.add(corrente);
            postiSala--;
            System.out.println("+++" + corrente.getName() + " entra in sala di preparazione e aspetta di essere chiamato dal chirurgo.");

            //System.out.println(possoEntrare + " AIA ALLA MADONNAAAAAAAAAAA  ");
            while (salaPreparazione.getFirst() != corrente || !possoEntrare)
            {
                //System.out.println(possoEntrare + " AIA AL SIGNOREEEEEEEEEEEEEEEE  ");
                attesaPreparazione.await();
            }
            possoEntrare = false;
            salaPreparazione.removeFirst();
            postiSala++;
            codaIngresso.signalAll();


            System.out.println("+++" + corrente.getName() + " entra in sala operatoria.");
            possoOperare = true;
            operare.signal();


        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void pazinteEsci()
    {
        monitor.lock();
        try
        {
            while (!possoUscire)
                uscire.await();
            possoUscire = false;
            System.out.println("+++" + Thread.currentThread().getName() + " sta uscendo dalla sala operatoria.");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            monitor.unlock();
        }
    }
}
