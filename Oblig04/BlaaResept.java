class BlaaResept extends Resept
{
  public double prisAaBetale() {return drug.hentPris()*0.25;}
  public String farge() {return "blaa";}

  public BlaaResept(Legemiddel pDrug, Lege pPrescribingDoc, int pPatientID, int pRemaining)
  {
    super(pDrug, pPrescribingDoc, pPatientID, pRemaining);
  }

}
