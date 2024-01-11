package sisop.tracceEsami.casello;

public class Test
{
    public static void main(String[] args)
    {
        int numeroPorte = 3;
        int tariffa = 2;
        Casello c = new CaselloMonitor(numeroPorte, tariffa);
        int numeroAuto = 10;
        Automobile[] automobili = new Automobile[numeroAuto];

        for (int i = 0; i < numeroAuto; i++)
        {
            automobili[i] = new Automobile(c, ""+i);
            automobili[i].start();
        }
    }
}
