package sisop.tracceEsami.funivia;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class FuniviaConcreta extends Funivia
{
    private Semaphore mutex, posti, pedone, ciclista, possoScendere;
    private List<Thread> cabina;

    public FuniviaConcreta(int postiLiberi)
    {
        super(postiLiberi);

        this.mutex = new Semaphore(1);
        this.posti = new Semaphore(postiLiberi);
        this.pedone = new Semaphore(0);
        this.ciclista = new Semaphore(0);
        this.possoScendere = new Semaphore(0);

        this.cabina = new LinkedList<>();
    }
    @Override
    public void pilotaStart()
    {
        try
        {
            System.out.print("Il pilota sta caricando dei ");
            if ((viaggio % 2) == 0)
            {
                System.out.println("ciclisti.");
                System.out.println("---NUMERO PERMESSI CICLISTI: " + ciclista.availablePermits());
                while(cabina.size() < postiLiberi/2)
                    if(ciclista.availablePermits() == 0)
                        ciclista.release();
                ciclista.drainPermits();
                System.out.println("---NUMERO PERMESSI CICLISTI: " + ciclista.availablePermits());
            }
            else
            {
                System.out.println("pedoni.");
                System.out.println("---NUMERO PERMESSI PEDONI: " + pedone.availablePermits());
                while(cabina.size() < postiLiberi)
                    if(pedone.availablePermits() == 0)
                        pedone.release();
                pedone.drainPermits();
                System.out.println("---NUMERO PERMESSI PEDONI: " + pedone.availablePermits());
            }
            this.viaggio++;
            System.out.println("La funivia sta partendo per il suo " + (viaggio + 1) + " viaggio.");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void pilotaEnd()
    {
        try
        {
            System.out.println("La funivia ha concluso il suo " + viaggio + " viaggio. Il pilota fa scendere i " + ((viaggio % 2 == 0)?" pedoni.":" ciclisti.") );
            while(cabina.size() > 0)
            {
                posti.release();
                mutex.acquire();
                System.out.println("Sta scendendo il passeggero: " + cabina.remove(0));
                mutex.release();
                possoScendere.release();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void turistaSali(int t)
    {
        try
        {
            if(t == PEDONE)
            {
                pedone.acquire();
                posti.acquire();
                mutex.acquire();
                cabina.add(Thread.currentThread());
                mutex.release();
                System.out.println("Sta salendo un pedone");
            }
            else
            {
                ciclista.acquire();
                posti.acquire(2);
                mutex.acquire();
                cabina.add(Thread.currentThread());
                mutex.release();
                System.out.println("Sta salendo un ciclista");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void turistaScendi(int t)
    {
        try
        {
            possoScendere.acquire();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        if(t == PEDONE)
        {
            System.out.println("Sta scendendo un pedone");
        }
        else
        {
            System.out.println("Sta scendendo un ciclista");
        }
    }
}
