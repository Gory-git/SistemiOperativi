package sisop.tracceEsami.aziendaAgricola;

public class Test
{
    public static void main(String[] args)
    {
        AziendaAgricola aziendaAgricola = new AziendaAgricolaSemafori(10, 3);
        //AziendaAgricola aziendaAgricola = new AziendaAgricolaMonitor(10, 3);
        Magazziniere magazziniere = new Magazziniere("magazziniere 1", aziendaAgricola);
        magazziniere.setDaemon(true);
        magazziniere.start();
        int numeroClienti = 10;
        Cliente[] clienti = new Cliente[numeroClienti];
        for (int i = 0; i < numeroClienti; i++)
        {
            clienti[i] = new Cliente("Cliente " + i, aziendaAgricola);
            clienti[i].start();
        }
        for (int i = 0; i < numeroClienti; i++)
        {
            try
            {
                clienti[i].join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(aziendaAgricola.getIncassoGiornaliero());
    }
}
