package sisop.threads.esercizi4;

import java.util.concurrent.Semaphore;

public class Matrice extends Thread
{
    private static final Semaphore righe = new Semaphore(1);
    private static final Semaphore colonne = new Semaphore(1);

    private final int[][] matrix;
    private final int indice;
    private final boolean riga;
    public Matrice(int[][] matrix, int indice, boolean riga)
    {
        this.matrix = matrix;
        this.indice = indice;
        this.riga = riga;
    }

    public void run()
    {
        try
        {
            if (riga)
            {
                righe.acquire();
                for (int i = 0; i < matrix[indice].length; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        matrix[indice][i] -= 1;
                    }
                }
                righe.release();
            }else
            {
                righe.acquire();
                for (int i = 0; i < matrix.length; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        matrix[i][indice] += 1;
                    }
                }
                righe.release();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
