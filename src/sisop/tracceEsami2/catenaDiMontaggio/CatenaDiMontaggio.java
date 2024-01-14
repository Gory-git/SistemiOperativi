package sisop.tracceEsami2.catenaDiMontaggio;

public abstract class CatenaDiMontaggio
{
    public abstract void richiediProduzione(int pezziSx, int pezziDx) throws Exception;
    public abstract void produci(int tipo) throws Exception;
    public abstract void assembla() throws Exception;
}
