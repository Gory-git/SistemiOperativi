package sisop.tracceEsami1.muratori1;

public class Mutratore extends Thread
{
    private Casa casa;
    private int tipo;
    public Mutratore(String nome, Casa casa, int tipo)
    {
        super(nome);
        this.casa = casa;
        this.tipo = tipo;
    }

    @Override
    public void run()
    {
        while(casa.inizia(tipo))
            casa.termina(tipo);
        System.out.println(getName() + " sta scapilando");
    }
}
