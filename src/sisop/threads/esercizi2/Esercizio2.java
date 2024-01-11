package sisop.threads.esercizi2;

public class Esercizio2
{
    public static void main(String[] args) {
        int[] a = {4, 3, 4, 5};
        int[] b = {1, 2, 6, 7};

        int res = 0;
        int m = 2;

        for (int i = 0; i <= a.length - m; i+= m)
        {
            System.out.println("i " + i);

            ProdottoScalare p = new ProdottoScalare(a, b, i, i + 2);
            p.start();
            res += p.getProdotto();
        }

        System.out.println(res);

    }

}

class ProdottoScalare extends Thread
{
    private int[] x, y;

    private int da, a;
    private int prodotto;
    public ProdottoScalare(int[] x, int[] y, int da, int a)
    {
        this.x = x;
        this.y = y;
        this.da = da;
        this.a = a;
        this.prodotto = 0;
    }

    public void run()
    {
        for (int i = da; i < a; i++)
        {
            prodotto += x[i] * y[i];
        }
    }

    public int getProdotto()
    {
        try {
            this.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return this.prodotto;
    }
}