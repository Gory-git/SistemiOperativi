package sisop.tracceEsami.visualizzatore;

public class Test
{
    public static void main(String[] args)
    {
        int numeroUtenti = 10;
        int lunghezzaCoda = 100;

        //Coda coda = new CodaSemafori(lunghezzaCoda);
        Coda coda = new CodaMonitor(lunghezzaCoda);

        Utente[] utenti = new Utente[numeroUtenti];
        for (int i = 0; i < numeroUtenti; i++)
        {
            utenti[i] = new Utente(coda, "Utente " + i);
            utenti[i].start();
        }

        Visualizzatore visualizzatore = new Visualizzatore(coda);
        visualizzatore.start();


    }

}
