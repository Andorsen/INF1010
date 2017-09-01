class Node<T>
{
  private Node<T> next;
  private Node<T> previous;
  private T data;

  public Node(T pData)
  {
    data = pData;
  }

  public Node()
  {
    this(null);
  }

  public void setNext(Node<T> pNext)
  {
    next = pNext;
  }

  public void setPrevious(Node<T> pPrevious)
  {
    previous = pPrevious;
  }

  public Node<T> getNext()
  {
    return next;
  }

  public Node<T> getPrevious()
  {
    return previous;
  }

  public T get()
  {
    return data;
  }

  public T remove()
  {
    next.setPrevious(previous);
    previous.setNext(next);
    return data;
  }

  public boolean insert(Node<T> left, Node<T> right)
  {
    if(left.getNext() != right || right.getPrevious() != left) return false;

    next = right;
    previous = left;
    left.setNext(this);
    right.setPrevious(this);
    return true;
  }
}
