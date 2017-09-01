class HvitResept extends Resept
{
  public double prisAaBetale() {return drug.hentPris();}
  public String farge() {return "hvit";}

  public HvitResept(Legemiddel pDrug, Lege pPrescribingDoc, int pPatientID, int pRemaining)
  {
    super(pDrug, pPrescribingDoc, pPatientID, pRemaining);
  }

}
