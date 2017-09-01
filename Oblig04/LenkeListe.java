import java.util.*;

abstract class LenkeListe<T> implements Liste<T>
{
  protected int size;
  protected Node<T> head;
  protected Node<T> tail;

  public LenkeListe()
  {
    size = 0;
    head = new Node<T>();
    tail = new Node<T>();
    head.setNext(tail);
    tail.setPrevious(head);
  }

  public int storrelse()
  {
    return size;
  }

  protected void addFirst(Node<T> n)
  {
    size++;
    n.insert(head, head.getNext());
  }

  protected void addLast(Node<T> n)
  {
    size++;
    n.insert(tail.getPrevious(), tail);
  }

  protected T removeFirst()
  {
    if(size<1) return null;
    size--;
    return head.getNext().remove();
  }

  protected T removeLast()
  {
    if(size<1) return null;
    size--;
    return tail.getPrevious().remove();
  }

  public boolean erTom()
  {
    return size==0;
  }

  public void settInn(T element)
  {

  }

  public T fjern()
  {
    return null;
  }

  public Listerator<T> iterator()
  {
    return new Listerator<T>(head);
  }

  protected class Listerator<T> implements Iterator<T>
  {
    private Node<T> cursor;

    public Listerator(Node<T> start)
    {
      cursor = start;
    }

    public boolean hasNext()
    {
      return cursor.getNext().get() != null;
    }

    public T next()
    {
      if(!hasNext()) return null;
      cursor = cursor.getNext();
      return cursor.get();
    }

    public void insert(Node<T> n)
    {
      n.insert(cursor.getPrevious(), cursor);
    }

    public void remove()
    {

    }
  }
}
