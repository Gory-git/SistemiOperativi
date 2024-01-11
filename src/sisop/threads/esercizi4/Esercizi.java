package sisop.threads.esercizi4;

import java.util.Arrays;

public class Esercizi
{

    private static final Stampante a = new Stampante("A");
    private static final Stampante b = new Stampante("B");

    public static void main(String[] args)
    {
        //esercizio1();
        //esercizio2();
        //esercizio3();
        //esercizio4();

    }

    private static void esercizio1()
    {
            new Stampante("A").start();
            new Stampante("B").start();
    }

    private static void esercizio2()
    {
        while(true)
        {
            new Stampante("A").start();
            new Stampante("B").start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void esercizio3()
    {
        int[][] matrix = new int[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                matrix[i][j] = 0;
            }
            System.out.println(Arrays.toString(matrix[i]));
        }

        for (int i = 0; i < 10; i++)
            new Matrice(matrix, i, true).start();

        for (int j = 0; j < 10; j++)
            new Matrice(matrix, j, false).start();

        System.out.println();
        for (int i = 0; i < 10; i++)
            System.out.println(Arrays.toString(matrix[i]));
    }

    private static void esercizio4()
    {
        while(true)
        {
            new Stampante("A").start();
            new Stampante("A").start();
            new Stampante("B").start();
        }
    }

    private static void esercizio6(int tipo)
    {

    }
}
