package sisop.tracceEsami.visualizzatore;

import java.util.Arrays;
import java.util.Random;

public class Utente extends Thread
{
    private Coda coda;

    public Utente(Coda coda, String nome)
    {
        super(nome);
        this.coda = coda;
    }

    public void run() {
        while (true)
        {
            String[] stringa = ottieniStringhe();
            //System.out.println(getName() + " vuole scrivere " + Arrays.toString(stringa));
            coda.put(stringa);
            try {
                sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] ottieniStringhe()
    {
        Random random = new Random();
        int dim = random.nextInt(1, 6);
        String[] ret = new String[dim];
        for (int i = 0; i < dim; i++)
        {
            ret[i] = this.getName() + " --> " + i;
        }
        return ret;
    }

}
