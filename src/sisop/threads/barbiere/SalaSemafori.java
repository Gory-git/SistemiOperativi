package sisop.threads.barbiere;

import java.util.concurrent.Semaphore;

public class SalaSemafori extends Sala
{
    private Semaphore possoTagliare = new Semaphore(1);
    private Semaphore poltrona = new Semaphore(1, true);
    private Semaphore mutex = new Semaphore(1, true);
    public SalaSemafori(int numeroPosti)
    {
        super(numeroPosti);
    }

    @Override
    public void tagliaCapelli() throws Exception
    {

    }

    @Override
    public void attendiTaglio() throws Exception
    {

    }
}
