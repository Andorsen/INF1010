class Pasient
{
  static protected int lastID = 0;

  private String name;
  private long personalID;
  private String address;
  private int zipCode;
  private int patientID;
  private Stabel<Resept> prescriptions = new Stabel<>();

  public Pasient(String pName, long pPersonalID, String pAddress, int pZipCode)
  {
    name = pName;
    personalID = pPersonalID;
    address = pAddress;
    zipCode = pZipCode;
    patientID = lastID;
    lastID++;
  }

  public String toString()
  {
    return name+" Pasient ID: "+patientID+" Personnr.: "+personalID;
  }


  public int hentId() {return patientID;}
  public String hentNavn() {return name;}
  public long hentFodselsnummer() {return personalID;}
  public String hentGateadresse() {return address;}
  public int hentPostnummer() {return zipCode;}
  public void leggTilResept(Resept resept) {prescriptions.settInn(resept);}
  public Stabel<Resept> hentReseptliste() {return prescriptions;}
}
