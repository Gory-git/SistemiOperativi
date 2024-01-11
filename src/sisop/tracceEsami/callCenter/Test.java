package sisop.tracceEsami.callCenter;

public class Test
{
    public static void main(String[] args)
    {
        int numeroClienti = 50;
        int numeroOperatori = 20;
        CallCenter callCenter = new CallCenterMonitor(numeroClienti, numeroOperatori);

        Cliente[] clienti = new Cliente[numeroClienti];
        for (int i = 0; i < numeroClienti; i++)
        {
            clienti[i] = new Cliente(("Cliente " + i), callCenter);
            clienti[i].start();
        }
        Operatore[] operatori = new Operatore[numeroOperatori];
        for (int i = 0; i < numeroOperatori; i++)
        {
            operatori[i] = new Operatore("Operatore " + i, callCenter);
            operatori[i].setDaemon(true);
            operatori[i].start();
        }
    }
}
