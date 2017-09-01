class DynamiskTabell<T> extends StatiskTabell<T>
{
  public DynamiskTabell(int pSize)
  {
    super(pSize);
  }
  public DynamiskTabell()
  {
    super();
  }
  /**
  * @override
  */
  public void settInn(T element)
  {
    if(index>=tabell.length) resize();
    tabell[index++] = element;
  }

  private void resize()
  {
    Object[] temp = (T[]) new Object[tabell.length+10];
    for(int i=0; i<index; i++) temp[i] = tabell[i];
    tabell = temp;
  }
}
