abstract class Resept
{
  protected static int lastPrescrID = 0;
  protected Legemiddel drug;
  protected Lege prescribingDoc;
  protected int patientID;
  protected int remaining;
  protected int prescrID;

  public Resept(Legemiddel pDrug, Lege pPrescribingDoc, int pPatientID, int pRemaining)
  {
    drug = pDrug;
    prescribingDoc = pPrescribingDoc;
    patientID = pPatientID;
    remaining = pRemaining;
    prescrID = lastPrescrID;
    lastPrescrID++;
  }

  public String toString() {return "Legemiddel: "+drug.hentNavn()+" For pasientID:" + patientID+ " Forskrevet av lege: "+prescribingDoc.hentNavn();}

  public int hentId() {return prescrID;}
  public Legemiddel hentLegemiddel() {return drug;}
  public Lege hentLege() {return prescribingDoc;}
  public int hentPasientId() {return patientID;}
  public int hentReit() {return remaining;}

/**
* Bruker resepten én gang. Returner false om resepten er
* oppbrukt, ellers returnerer den true.
* @return om resepten kunne brukes
*/
public boolean bruk() {return (remaining--)>0;}
/**
* Returnerer reseptens farge. Enten "blå" eller "hvit".
* @return reseptens farge
*/
abstract public String farge();
/**
* Returnerer prisen pasienten maa betale.
* @return prisen pasienten maa betale
*/
abstract public double prisAaBetale();

}
