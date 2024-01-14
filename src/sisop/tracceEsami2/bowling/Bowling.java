package sisop.tracceEsami2.bowling;

public abstract class Bowling
{
    public final int NUMERO_GIOCATORI, TIRI_MASSIMI;
    public int[] numeroTiri;
    protected Bowling(int numeroGiocatori, int tiriMassimi)
    {
        NUMERO_GIOCATORI = numeroGiocatori;
        TIRI_MASSIMI = tiriMassimi;

        numeroTiri = new int[numeroGiocatori];
        for (int i = 0; i < numeroGiocatori; i++)
        {
            numeroTiri[i] = 0;
        }
    }

    public abstract String fornisciInformazioni() throws Exception;
    public abstract void preparaPartita() throws Exception;
    public abstract void gioca(String info) throws Exception;
    public abstract void deposita() throws Exception;
}
