package customLists;

public class Stabel<T> extends LenkeListe<T>
{
  /**
  * @override
  */
  public void add(T element)
  {
    addFirst(new Node<T>(element));
  }
  public T fjern()
  {
    return removeFirst();
  }
}
