package sisop.threads.barbiere;

public abstract class Sala
{
    protected int numeroPosti;

    public Sala(int numeroPosti)
    {
        this.numeroPosti = numeroPosti;
    }

    public abstract void tagliaCapelli() throws Exception;
    public abstract void attendiTaglio() throws Exception;
}
