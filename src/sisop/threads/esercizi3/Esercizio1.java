package sisop.threads.esercizi3;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Esercizio1
{
    public static void main(String[] args)
    {
        int n = 5;
        int m = 10;

        AtomicInteger[][] matrix = new AtomicInteger[n][m];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                matrix[i][j] = new AtomicInteger(0);
            }
        }

        Operatore[] operatoriRighe = new Operatore[n];
        for (int i = 0; i < n; i++)
        {
            operatoriRighe[i] = new Operatore(matrix, i, true);
            operatoriRighe[i].start();
        }

        Operatore[] operatoriColonne = new Operatore[m];
        for (int i = 0; i < m; i++)
        {
            operatoriColonne[i] = new Operatore(matrix, i, false);
            operatoriColonne[i].start();
        }


        try {
            for (int i = 0; i < n; i++) {
                operatoriRighe[i].join();
            }
            for (int i = 0; i < m; i++) {
                operatoriColonne[i].join();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        System.out.println(Arrays.deepToString(matrix));
    }
}

class Operatore extends Thread
{
    private AtomicInteger[][] matrix;
    private int indice;
    private boolean riga;
    public Operatore(AtomicInteger[][] matrix, int indice, boolean riga)
    {
        this.matrix = matrix;
        this.indice = indice;
        this.riga = riga;
    }

    public void run()
    {
        if(riga)
        {
            for (int i = 0; i < matrix[indice].length; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    matrix[indice][i].addAndGet(-1);
                }
            }
        }else
        {
            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    matrix[i][indice].addAndGet(1);
                }
            }
        }
    }

}