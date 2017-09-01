abstract class Legemiddel
{
  protected static int lastMedID = 0;
  protected String name;
  protected double price;
  protected double concentration;
  protected int medID;

  public Legemiddel(String pName, double pPrice, double pConcentration)
  {
    name = pName;
    price = pPrice;
    concentration = pConcentration;
    medID = lastMedID;
    lastMedID++;
  }

  public String toString(){ return "Legemiddel: "+name+" ID: "+medID;}

  public int hentId() {return medID;}
  public String hentNavn() {return name;}
  public double hentPris() {return price;}
  public double hentVirkestoff() {return concentration;}
}
