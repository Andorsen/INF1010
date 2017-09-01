class LegemiddelA extends Legemiddel
{
  private int narcoticGrade;

  public LegemiddelA(String pName, double pPrice, double pConcentration, int pNarcoticGrade)
  {
    super(pName, pPrice, pConcentration);
    narcoticGrade = pNarcoticGrade;
  }

  public int hentNarkotiskStyrke() {return narcoticGrade;}
}
