package sisop.tracceEsami2.bowling;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BowlingSemafori extends Bowling
{
    private Random random = new Random();
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore possoPreparare = new Semaphore(0);
    private Semaphore possoChiudere = new Semaphore(0);
    private int contatoreGiocatoriArrivati = 0;
    private HashMap<Thread, Integer> giocatoreIndice = new HashMap<>();
    private HashMap<Thread, String> giocatoreInfo = new HashMap<>();
    private HashMap<Thread, Integer> giocatorePunteggio = new HashMap<>();
    private Semaphore[] possoTirare;
    private Semaphore[] possoDepositare;


    protected BowlingSemafori(int numeroGiocatori, int tiriMassimi)
    {
        super(numeroGiocatori, tiriMassimi);
        possoTirare = new Semaphore[numeroGiocatori];
        possoDepositare = new Semaphore[numeroGiocatori];
        for (int i = 0; i < numeroGiocatori; i++)
        {
            possoTirare[i] = new Semaphore(0);
            possoDepositare[i] = new Semaphore(0);
        }
    }

    @Override
    public String fornisciInformazioni() throws Exception
    {

        Thread corrente = Thread.currentThread();
        String info = corrente.getName();
        mutex.acquire();
        giocatoreInfo.put(corrente, info);
        giocatoreIndice.put(corrente, contatoreGiocatoriArrivati);
        giocatorePunteggio.put(corrente, 0);
        contatoreGiocatoriArrivati++;
        if(contatoreGiocatoriArrivati == NUMERO_GIOCATORI)
            possoPreparare.release();
        mutex.release();
        return info;
    }

    @Override
    public void preparaPartita() throws Exception
    {
        possoPreparare.acquire();
        mutex.acquire();
        for(Thread k : giocatoreInfo.keySet())
            System.out.println("Consegno scarpe : " + giocatoreInfo.get(k));
        mutex.release();
        possoTirare[0].release();
    }

    @Override
    public void gioca(String info) throws Exception
    {
        Thread corrente = Thread.currentThread();
        mutex.acquire();
        int indice = giocatoreIndice.get(corrente);
        mutex.release();
        while (numeroTiri[indice] <10)
        {
            possoTirare[indice].acquire();
            int punti = random.nextInt(0, 11);
            mutex.acquire();
            numeroTiri[indice]++;
            giocatorePunteggio.put(corrente, giocatorePunteggio.get(corrente) + punti);
            mutex.release();
            possoTirare[(indice+1)%NUMERO_GIOCATORI].release();
        }
        possoDepositare[indice].acquire();
        System.out.println("Numero di scarpe consegnato da: " + corrente.getName());
        if(indice<NUMERO_GIOCATORI-1)
            possoDepositare[(indice+1)%NUMERO_GIOCATORI].release();
        else
            possoChiudere.release();
    }

    @Override
    public void deposita() throws Exception
    {
        possoDepositare[0].release();
        possoChiudere.acquire();
        Thread vincitore = null;
        int puntiMax = 0;
        for (Thread k : giocatorePunteggio.keySet())
        {
            int puntiK = giocatorePunteggio.get(k);
            if (puntiK > puntiMax)
            {
                puntiMax = puntiK;
                vincitore = k;
            }

        }
        System.out.println("Tutte le scarpe consegnate. Il vincitore di oggi : "+ vincitore.getName() +". Bowling Il Semaforo chiude!");
    }
}
