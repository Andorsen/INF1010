class OrdnetLenkeliste<T extends Comparable<T>> extends LenkeListe<T>
{
  /**
  * @override
  */
  public void settInn(T element)
  {
    Node<T> n = new Node<T>(element);
    if(size==0) addFirst(n);
    else
    {
      Listerator<T> it = iterator();
      int counter = 0;
      while(it.hasNext())
      {
        counter++;
        if(it.next().compareTo(element)>=0 || counter>=size)
        {
          size++;
          it.insert(n);
          break;
        }
      }
    }
  }

  /**
  * @override
  */
  public T fjern()
  {
    return removeFirst();
  }
}
