package sisop.tracceEsami.funivia;

public class Test
{
    public static void main(String[] args)
    {
        int posti = 6;

        int numPedoni = 18;
        int numCiclisti = 9;

        Funivia funivia = new FuniviaConcreta(posti);

        Turista[] pedoni = new Turista[numPedoni];
        Turista[] ciclisti = new Turista[numCiclisti];
        for (int i = 0; i < numPedoni; i++)
        {
            pedoni[i] = new Turista(("Pedone " + i), 0, funivia);
            pedoni[i].start();
        }
        for (int i = 0; i < numCiclisti; i++)
        {
            ciclisti[i] = new Turista(("Ciclista " + i), 1, funivia);
            ciclisti[i].start();
        }

        Pilota pilota = new Pilota(funivia);
        pilota.setDaemon(true);
        pilota.start();

    }
}
