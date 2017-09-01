class Koe<T> extends LenkeListe<T>
{
  /**
  * @override
  */
  public void settInn(T element)
  {
    addLast(new Node<T>(element));
  }

  /**
  * @override
  */
  public T fjern()
  {
    return removeFirst();
  }
}
