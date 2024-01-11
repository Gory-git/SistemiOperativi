package sisop.tracceEsami.bar;

public class Test
{
    public static void main(String[] args)
    {
        int numeroClienti = 5;
        int numeroBarista = 1;
        Bar bar = new BarSemafori(numeroClienti);
        for (int i = 0; i < numeroBarista; i++)
        {
            new Barista(""+i, bar).start();

        }

        for (int i = 0; i < numeroClienti; i++)
        {
            new Cliente(""+i, bar).start();

        }
    }
}
