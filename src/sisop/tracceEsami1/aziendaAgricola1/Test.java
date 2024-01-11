package sisop.tracceEsami1.aziendaAgricola1;

public class Test
{
    public static void main(String[] args)
    {
        int numeroSacchi = 200;
        int costoSacco = 3;
        int numeroClienti = 100;
        Cliente[] clienti = new Cliente[numeroClienti];
        AziendaAgricola aziendaAgricola = new AziendaAgricolaMonitor(numeroSacchi, costoSacco);
        Magazziniere magazziniere = new Magazziniere(aziendaAgricola);
        magazziniere.setDaemon(true);
        magazziniere.start();
        int tot = 0;
        for (int i = 0; i < numeroClienti; i++)
        {
            clienti[i] = new Cliente(""+i, aziendaAgricola);
            tot += clienti[i].quantita;
            clienti[i].start();
            System.out.println("Cliente " + i + " partito");
        }
        for (int i = 0; i < numeroClienti; i++)
        {
            try
            {
                clienti[i].join();
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

        System.out.println(aziendaAgricola.incasso);
        System.out.println(tot);
    }
}
