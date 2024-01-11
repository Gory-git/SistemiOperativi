package sisop.tracceEsami.callCenter;

import java.util.Random;

public class Operatore extends Thread
{
    private CallCenter callCenter;
    public Operatore(String nome, CallCenter callCenter)
    {
        super(nome);
        this.callCenter = callCenter;
    }

    @Override
    public void run()
    {
        int chiamate = 0;
        while (true)
        {
            callCenter.fornisciAssistenza();
            try
            {
                sleep(new Random().nextInt(1, 11) * 10000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            callCenter.prossimoCliente();
            if(chiamate == 15)
            {
                chiamate = 0;
                System.out.println(getName() + " si prende una pausa dopo aver aiutato 15 clienti");
                try
                {
                    sleep(50000);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                System.out.println(getName() + " termina la pausa");
            }
        }
    }
}
