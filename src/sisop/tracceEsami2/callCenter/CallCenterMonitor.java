package sisop.tracceEsami2.callCenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenterMonitor extends CallCenter
{
    private Lock monitor = new ReentrantLock();

    public CallCenterMonitor(int numeroOperatori, int numeroClienti)
    {
        super(numeroOperatori, numeroClienti);
    }


    @Override
    public void richiediAssistenza()  throws Exception
    {
        monitor.lock();
        try
        {

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
