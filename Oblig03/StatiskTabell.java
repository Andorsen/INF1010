import java.util.*;

class StatiskTabell<T> implements Tabell<T>
{
  protected Object[] tabell;
  protected int index;

  public StatiskTabell()
  {
    this(10);
  }

  public StatiskTabell(int newSize)
  {
    index = 0;
    tabell = (T[]) new Object[newSize];
  }

  public boolean erTom()
  {
    return index == 0;
  }

  public int storrelse()
  {
    return index;
  }

  public void settInn(T element)
  {
    if(index>=tabell.length) throw new FullTabellUnntak(tabell.length);
    tabell[index++] = element;
  }

  public T hentFraPlass(int plass)
  {
    if(plass>=0 && plass<index) return (T)tabell[plass];
    throw new UgyldigPlassUnntak(plass, index);
  }

  public Iterator<T> iterator()
  {
    return new TabellIterator<T>(index);
  }

  protected class TabellIterator<T> implements Iterator<T>
  {
    private int cursor;
    private int end;

    public TabellIterator(int pEnd)
    {
      cursor = 0;
      end = pEnd;
    }

    public boolean hasNext()
    {
      return cursor<end;
    }

    public T next()
    {
      if(hasNext()) return (T)StatiskTabell.this.tabell[cursor++];
      else return null;
    }

    /**
    * Not used in this program
    */
    public void remove()
    {

    }
  }
}
