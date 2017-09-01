package customLists;

public class Queue<T> extends LenkeListe<T>
{
  /**
  * @override
  */
  public void add(T element)
  {
    addLast(new Node<T>(element));
  }

  /**
  * @override
  */
  public T poll()
  {
    return removeFirst();
  }

  public void settInn()
  {
    
  }
}
