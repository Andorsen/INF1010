class Stabel<T> extends LenkeListe<T>
{
  /**
  * @override
  */
  public void settInn(T element)
  {
    addFirst(new Node<T>(element));
  }
  public T fjern()
  {
    return removeFirst();
  }
}
