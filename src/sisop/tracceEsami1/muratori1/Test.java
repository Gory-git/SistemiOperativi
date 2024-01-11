package sisop.tracceEsami1.muratori1;

public class Test
{
    public static void main(String[] args)
    {
        Casa casa = new CasaMonitor(20, 4);

        Mutratore[] mattoni = new Mutratore[5];


        for (int i = 0; i < 5; i++)
        {
            mattoni[i] = new Mutratore("Muratore mattoni "+ i, casa, 0);
            mattoni[i].start();
        }
        Mutratore[] cemento = new Mutratore[7];
        for (int i = 0; i < 7; i++)
        {
            cemento[i] = new Mutratore("Muratore cemento "+ i, casa, 1);
            cemento[i].start();
        }
    }
}
