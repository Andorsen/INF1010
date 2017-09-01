class Fastlege extends Lege implements Kommuneavtale
{
  int contractID;

  public Fastlege(String pName, int pContractID)
  {
    super(pName);
    contractID = pContractID;

  }

  public int hentAvtalenummer() {return contractID;}
}
