package sisop.threads.esercizi3;

import java.util.concurrent.atomic.AtomicInteger;

public class ContoCorrente
{
    private AtomicInteger deposito;

    public ContoCorrente(int deposito)
    {
        this.deposito = new AtomicInteger(deposito);
    }

    public int deposita(int somma)
    {
        return deposito.addAndGet(somma);
    }

    public int preleva(int somma)
    {
        return deposito.addAndGet(-somma);
    }

    public int getDeposito() {
        return deposito.get();
    }
}


class Correntista extends Thread
{
    private String nome;
    private ContoCorrente cc;
    private int numeroOperazioni, valoreOperazioni;

    public Correntista(String nome, ContoCorrente cc, int numeroOperazioni, int valoreOperazioni)
    {
        this.nome = nome;
        this.cc = cc;
        if(numeroOperazioni % 2 != 0)
            throw new IllegalArgumentException("Operazioni dispari");
        this.numeroOperazioni = numeroOperazioni;
        this.valoreOperazioni = valoreOperazioni;
    }

    public void run()
    {
        for (int i = 0; i < numeroOperazioni; i++)
        {
            if(i % 2 == 0)
                cc.preleva(valoreOperazioni);
            else
                cc.deposita(valoreOperazioni);
        }
        System.out.println("Il correntista: " + nome + " ha concluso le operazioni");
    }
}

class CorrentistaSafe extends Thread
{
    private String nome;
    private ContoCorrente cc;
    private int numeroOperazioni, valoreOperazioni;

    public CorrentistaSafe(String nome, ContoCorrente cc, int numeroOperazioni, int valoreOperazioni)
    {
        this.nome = nome;
        this.cc = cc;
        if(numeroOperazioni % 2 != 0)
            throw new IllegalArgumentException("Operazioni dispari");
        this.numeroOperazioni = numeroOperazioni;
        this.valoreOperazioni = valoreOperazioni;
    }

    public void run()
    {
        for (int i = 0; i < numeroOperazioni; i++)
        {
            if(i % 2 == 0)
                cc.preleva(valoreOperazioni);
            else
                cc.deposita(valoreOperazioni);
        }
        System.out.println("Il correntista: " + nome + " ha concluso le operazioni");
    }
}


class Test
{
    public static void main(String[] args)
    {
        int numeroCorrentisti = 200;
        int numeroOperazioni = 5000;
        int depositoIniziale = 10000;
        int valoreOperazioni = 100;

        ContoCorrente cc = new ContoCorrente(depositoIniziale);
        Correntista[] c = new Correntista[numeroCorrentisti];
        for (int i = 0; i < numeroCorrentisti; i++)
        {
            c[i] = new Correntista(""+i, cc, numeroOperazioni, valoreOperazioni);
            c[i].start();
        }

        for(Correntista correntista : c)
        {
            try {
                correntista.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (depositoIniziale == cc.getDeposito()) {
            System.out.println("OK " + depositoIniziale + " == " + cc.getDeposito());
        } else {
            System.out.println("NO " + depositoIniziale + " != " + cc.getDeposito());
        }



    }
}
