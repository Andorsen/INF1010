class Lege implements Comparable<Lege>
{
  protected String name;
  protected Koe<Resept> prescriptions = new Koe<>();

  public Lege(String pName)
  {
    name = pName;
  }

  public String hentNavn() {return name;}
  public int compareTo(Lege annenLege) {return name.compareTo(annenLege.hentNavn());}
  public void leggTilResept(Resept resept) {prescriptions.settInn(resept);}
  public Koe<Resept> hentReseptliste() {return prescriptions;}
}
