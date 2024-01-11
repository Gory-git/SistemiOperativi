package sisop.tracceEsami.aziendaAgricola;

public class Magazziniere extends Thread
{
    private AziendaAgricola aziendaAgricola;

    public Magazziniere(String nome, AziendaAgricola aziendaAgricola)
    {
        super(nome);
        this.aziendaAgricola = aziendaAgricola;
    }

    @Override
    public void run()
    {
        System.out.println(getName() + " all'opera");
        while (true)
        {
            aziendaAgricola.ripristina();
            try {
                sleep(/*600_000*/ 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Il magazziniere ha riempito il magazzino.");
        }

    }
}
