package sisop.tracceEsami2.bowling;

import java.util.Random;

public class Test
{
    private static final int NUMERO_GIOCATORI = 4;
    private static final int TIRI = 10;
    private static Giocatore[] giocatori = new Giocatore[NUMERO_GIOCATORI];
    private static Bowling bowling = new BowlingSemafori(NUMERO_GIOCATORI, TIRI);
    private static Operatore operatore = new Operatore("Operatore", bowling);
    public static void main(String[] args)
    {
        operatore.start();
        for (int i = 0; i < NUMERO_GIOCATORI; i++)
        {
            giocatori[i] = new Giocatore(""+i, bowling, new Random().nextInt(40, 50));
            giocatori[i].start();
        }
    }
}
