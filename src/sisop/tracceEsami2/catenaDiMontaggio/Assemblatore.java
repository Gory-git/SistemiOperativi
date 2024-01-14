package sisop.tracceEsami2.catenaDiMontaggio;

import java.util.LinkedList;

public class Assemblatore extends Thread
{
    private CatenaDiMontaggio catenaDiMontaggio;

    private static int[][] richieste = {{2,4}, {4,3}, {5,6}};
    public Assemblatore(String nome, CatenaDiMontaggio catenaDiMontaggio)
    {
        super(nome);
        this.catenaDiMontaggio = catenaDiMontaggio;
    }

    @Override
    public void run()
    {
        System.out.println("Assemblatore start");

        try
        {
            for (int i = 0; i < richieste.length; i++)
            {
                int[] richiesta = richieste[i];
                System.out.println("Inviata richiesta");
                catenaDiMontaggio.richiediProduzione(richiesta[0], richiesta[1]);
                catenaDiMontaggio.assembla();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
