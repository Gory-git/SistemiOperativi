package sisop.tracceEsami2.catenaDiMontaggio;

import java.util.LinkedList;

public class Test
{
    private static CatenaDiMontaggio catenaDiMontaggio = new CatenaDiMontaggioSemafori();
    private static final int NUMERO_PRODUTTORI_SX = 4;
    private static Produttore[] produttoriSX = new Produttore[NUMERO_PRODUTTORI_SX];
    private static final int NUMERO_PRODUTTORI_DX = 3;
    private static Produttore[] produttoriDX = new Produttore[NUMERO_PRODUTTORI_DX];
    public static void main(String[] args)
    {
        Assemblatore assemblatore = new Assemblatore("Assemblatore", catenaDiMontaggio);
        assemblatore.start();

        for (int i = 0; i < NUMERO_PRODUTTORI_SX; i++)
        {
            produttoriSX[i] = new Produttore(""+i, catenaDiMontaggio, 0);
            produttoriSX[i].setDaemon(true);
            produttoriSX[i].start();
        }

        for (int i = 0; i < NUMERO_PRODUTTORI_DX; i++)
        {
            produttoriDX[i] = new Produttore(""+i, catenaDiMontaggio, 1);
            produttoriDX[i].setDaemon(true);
            produttoriDX[i].start();
        }
    }
}
