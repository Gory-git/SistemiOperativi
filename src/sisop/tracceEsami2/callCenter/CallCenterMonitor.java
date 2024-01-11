package sisop.tracceEsami2.callCenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenterMonitor extends CallCenter
{
    private LinkedList<Thread> codaIngressoClienti = new LinkedList<>();
    private LinkedList<Thread> codaOperatoriLiberi = new LinkedList<>();
    private HashMap<Thread, Thread> clienteOperatore = new HashMap<>();
    private HashMap<Thread, Thread> operatoreCliente = new HashMap<>();
    private Lock monitor = new ReentrantLock();
    private Condition[] possoEntrare, possoRispondere;
    private Boolean[] entrare, rispondere;
    private HashMap<Thread, Integer> clienteIndice = new HashMap<>();
    private HashMap<Thread, Integer> operatoreIndice = new HashMap<>();
    private int contatoreClientiAttesa = 0;
    private int contatoreOperatoriLiberi = 0;




    public CallCenterMonitor(int numeroOperatori, int numeroClienti)
    {
        super(numeroOperatori, numeroClienti);

        possoRispondere = new Condition[numeroClienti];
        rispondere = new Boolean[numeroClienti];
        possoEntrare = new Condition[numeroOperatori];
        entrare = new Boolean[numeroOperatori];

        for (int i = 0; i < numeroOperatori; i++)
        {
            possoRispondere[i] = monitor.newCondition();
            rispondere[i] = false;
        }
        for (int i = 0; i < numeroClienti; i++)
        {
            possoEntrare[i] = monitor.newCondition();
            entrare[i] = false;
        }
    }


    @Override
    public void richiediAssistenza()  throws Exception
    {
        monitor.lock();
        try
        {
            codaIngressoClienti.add(Thread.currentThread());
            clienteIndice.put(Thread.currentThread(), contatoreClientiAttesa);
            contatoreClientiAttesa++;
            possoRispondere[operatoreIndice.get(codaOperatoriLiberi.getFirst())].signal();
            while (contatoreOperatoriLiberi == 0 || !entrare[clienteIndice.get(Thread.currentThread())] || !codaIngressoClienti.getFirst().equals(Thread.currentThread()))
            {
                possoEntrare[clienteIndice.get(Thread.currentThread())].await();
            }
        }finally
        {
            monitor.unlock();
        }

    }

    @Override
    public void fornisciAssistenza() throws Exception
    {
        monitor.lock();
        try
        {
            codaOperatoriLiberi.add(Thread.currentThread());
            operatoreIndice.put(Thread.currentThread(), contatoreOperatoriLiberi);
            contatoreOperatoriLiberi++;
            while ()
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void terminaChiamata() throws Exception
    {

    }

    @Override
    public void prossimoCliente() throws Exception
    {

    }
}
