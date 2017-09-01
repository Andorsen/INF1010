class Legeliste extends OrdnetLenkeliste<Lege>
{
  public Lege finnLege(String navn)
  {
    for(Lege l: this)
    {
      if(l.hentNavn().equals(navn)) return l;
    }
    System.out.printf("Lege \"%s\" finnes ikke..\n", navn);
    return null;
  }
/*  public Lege finnLege(String navn)
  {
    Listerator li = iterator();
    while(li.hasNext())
    {
      Lege temp = (Lege)li.next();
      if(temp.hentNavn().equals(navn)) return temp;
    }
    System.out.printf("Finner ikke lege: \"%s\"", navn);
    return null;
  }*/

  public String[] stringArrayMedNavn()
  {
    String[] names = new String[size];
    int i =0;
    for(Lege l: this) names[i++] = l.hentNavn();
    return names;
  }
}
