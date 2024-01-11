package sisop.tracceEsami.callCenter;

import java.util.concurrent.Semaphore;

public class CallCenterSemafori extends CallCenter
{
    private Semaphore clienti, operatrori;

    public CallCenterSemafori(int numeroClienti, int numeroOperatori)
    {
        super(numeroClienti, numeroOperatori);
        this.clienti = new Semaphore(numeroOperatori,true);
        this.operatrori = new Semaphore(numeroOperatori);
    }


    @Override
    public void richiediAssistenza()
    {
        try
        {
            operatrori.acquire();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void fornisciAssistenza()
    {
        try
        {
            clienti.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void terminaChiamata()
    {
        operatrori.release();
    }

    @Override
    public void prossimoCliente()
    {
        clienti.release();
    }
}
