package sisop.tracceEsami.callCenter;

import java.util.Random;

public class Cliente extends Thread
{
    private CallCenter callCenter;
    public Cliente(String nome, CallCenter callCenter)
    {
        super(nome);
        this.callCenter = callCenter;
    }

    @Override
    public void run()
    {
        callCenter.richiediAssistenza();
        try
        {
            sleep(new Random().nextInt(2, 7) * 10000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        callCenter.terminaChiamata();
    }
}
