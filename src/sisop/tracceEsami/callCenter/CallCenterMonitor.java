package sisop.tracceEsami.callCenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenterMonitor extends CallCenter
{
    private Lock monitor = new ReentrantLock();
    private Condition possoRispondere = monitor.newCondition();
    private HashMap<Thread, Thread> clientiOperatori = new HashMap<>();
    private HashMap<Thread, Integer> clientiIndice = new HashMap<>();
    private HashMap<Thread, Integer> operatoriIndice = new HashMap<>();
    private LinkedList<Thread> codaClienti = new LinkedList<>();
    private Condition[] possoParlare;
    private Condition[] possoChiudere;
    private boolean[] chiudere;
    private int indiceClienti = 0;
    private int indiceOperatori = 0;


    public CallCenterMonitor(int numeroClienti, int numeroOperatori)
    {
        super(numeroClienti, numeroOperatori);
        possoParlare = new Condition[numeroClienti];
        for (int i = 0; i < numeroClienti; i++)
        {
            possoParlare[i] = monitor.newCondition();
        }

        possoChiudere = new Condition[numeroOperatori];
        chiudere = new boolean[numeroOperatori];
        for (int i = 0; i < numeroOperatori; i++)
        {
            possoChiudere[i] = monitor.newCondition();
            chiudere[i] = false;
        }
    }
    @Override
    public void richiediAssistenza()
    {
        monitor.lock();
        try
        {
            Thread corrente = Thread.currentThread();
            System.out.println(corrente.getName() + " si mette in attesa di un operatore...");
            int i = indiceClienti;
            indiceClienti++;
            codaClienti.add(corrente);
            clientiIndice.put(corrente, i);
            clientiOperatori.put(corrente, null);
            possoRispondere.signalAll();
            while (clientiOperatori.get(corrente) == null)
                possoParlare[clientiIndice.get(corrente)].await();
            Thread operatore = clientiOperatori.get(corrente);
            System.out.println(corrente.getName() + " riceve assistenza da " + operatore.getName());
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void fornisciAssistenza()
    {
        monitor.lock();
        try
        {
            Thread corrente = Thread.currentThread();

            if(!operatoriIndice.keySet().contains(corrente))
            {
                int i = indiceOperatori;
                indiceOperatori++;
                operatoriIndice.put(corrente, i);
            }
            int i = operatoriIndice.get(corrente);
            chiudere[i] = false;
            while (codaClienti.size() == 0)
                possoRispondere.await();
            System.out.println(corrente.getName() + " si mette in attesa di un cliente...");
            Thread cliente = codaClienti.removeFirst();
            int indice = clientiIndice.get(cliente);
            clientiOperatori.put(cliente, corrente);
            possoParlare[indice].signal();
            System.out.println(corrente.getName() + " si mette a disposizione di " + cliente.getName());
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void terminaChiamata()
    {
        monitor.lock();
        try
        {
            Thread corrente = Thread.currentThread();
            Thread operatore = clientiOperatori.get(corrente);
            System.out.println(corrente.getName() + " ha ricevuto assistenza da " + operatore.getName() + " e sta chiudendo la chiamata.");
            int indiceOperatore = operatoriIndice.get(operatore);
            chiudere[indiceOperatore] = true;
            possoChiudere[indiceOperatore].signal();
        } finally
        {
            monitor.unlock();
        }
    }

    @Override
    public void prossimoCliente()
    {
        monitor.lock();
        try
        {
            Thread corrente = Thread.currentThread();
            int indice = operatoriIndice.get(corrente);
            while (!chiudere[indice])
                possoChiudere[indice].await();
            System.out.println(corrente.getName() + " ha dato assistenza al suo cliente.");
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            monitor.unlock();
        }
    }
}
