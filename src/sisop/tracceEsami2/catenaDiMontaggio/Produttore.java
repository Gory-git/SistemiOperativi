package sisop.tracceEsami2.catenaDiMontaggio;

import java.util.concurrent.TimeUnit;

public class Produttore extends Thread
{
    private CatenaDiMontaggio catenaDiMontaggio;
    private final int TIPO;
    public Produttore(String nome, CatenaDiMontaggio catenaDiMontaggio, int tipo)
    {
        super(nome);
        this.catenaDiMontaggio = catenaDiMontaggio;
        TIPO = tipo;
    }

    @Override
    public void run()
    {
        System.out.println("Produttore " + this.getName() + " tipo " + TIPO + " start");
        while (true)
        {
            try
            {
                catenaDiMontaggio.produci(TIPO);
                TimeUnit.MILLISECONDS.sleep(10+TIPO*5);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
