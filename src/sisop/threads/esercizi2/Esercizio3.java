package sisop.threads.esercizi2;

import java.util.HashMap;
import java.util.Map;

public class Esercizio3
{
    public static void main(String[] args)
    {
        int[][] matrix = {{2, 7, 2, 5, 2}, {2, 2, 9, 7, 2}, {1, 1, 1, 4, 1}, {2, 2, 2, 9, 2}};

        //System.out.println(Arrays.deepToString(matrix));

        Map<Integer, Integer> righe = new HashMap<>();
        Map<Integer, Integer> colonne = new HashMap<>();

        for (int i = 0; i < matrix.length; i++)
        {
            Controlla c = new Controlla(matrix, true, i);
            c.start();
            int val = c.getRet();
            righe.put(i,val);
        }

        //System.out.println(righe.toString());

        for (int i = 0; i < matrix[0].length; i++)
        {
            Controlla c = new Controlla(matrix, false, i);
            c.start();
            int val = c.getRet();
            colonne.put(i, val);
        }

        //System.out.println(colonne.toString());

        for(int j : righe.keySet())
        {
            for(int i : colonne.keySet())
            {
                if(righe.get(j) == i && j == colonne.get(i))
                {
                    //System.out.println(j);
                    //System.out.println(i);
                    System.out.println(matrix[j][i]);
                }
            }
        }
    }
}

class Controlla extends Thread
{
    int[][] matrix;
    private boolean riga;
    private int indice;
    private int ret;

    public Controlla(int[][] matrix, Boolean riga, int indice)
    {
        this.indice = indice;
        this.riga = riga;
        this.matrix = matrix;
        this.ret = matrix[0][0];
    }

    public void run()
    {
        if(riga)
        {
            int max = -1;
            for (int i = 0; i < matrix[0].length; i++)
            {
                if(matrix[indice][i] > max)
                {
                    max = matrix[indice][i];
                    ret = i;
                }
            }
        }else
        {
            int min = matrix[0][indice];
            for (int i = 0; i < matrix.length; i++)
            {
                if(matrix[i][indice] < min)
                {
                    min = matrix[i][indice];
                    ret = i;
                }
            }
        }
    }

    public int getRet() {
        try {
            this.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return ret;
    }
}
