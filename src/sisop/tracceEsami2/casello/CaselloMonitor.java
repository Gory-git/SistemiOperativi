package sisop.tracceEsami2.casello;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaselloMonitor extends Casello
{
    private Lock monitor = new ReentrantLock();
    private Condition[] porte;
    private LinkedList<Thread>[] codeAccesso;
    protected CaselloMonitor(int numeroPorte, int tariffa)
    {
        super(numeroPorte, tariffa);
        this.porte = new Condition[numeroPorte];
        for (int i = 0; i < numeroPorte; i++)
        {
            this.porte[i] = monitor.newCondition();
            this.codeAccesso[i] = new LinkedList<>();
        }
    }

    @Override
    public void accedi(int tratta, int porta) throws Exception
    {
        monitor.lock();
        try
        {
            while (!codeAccesso[porta].getFirst().equals(Thread.currentThread()))
                porte[porta].await();
            codeAccesso[porta].removeFirst();
            incasso += tratta * TARIFFA;
            porte[porta].signalAll();
        }finally
        {
            monitor.unlock();
        }
    }
}
