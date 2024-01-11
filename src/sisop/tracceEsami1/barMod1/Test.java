package sisop.tracceEsami1.barMod1;

public class Test
{
    public static void main(String[] args)
    {
        BarMod bar = new BarModSemafori(4);
        for (int i = 0; i < 100; i++)
        {
            new Cliente(("Cliente " + i), bar).start();
        }
    }
}
