class LegemiddelB extends Legemiddel
{
  private int addictiveGrade;

  public LegemiddelB(String pName, double pPrice, double pConcentration, int pAddictiveGrade)
  {
    super(pName, pPrice, pConcentration);
    addictiveGrade = pAddictiveGrade;
  }

  public int hentVanedannendeStyrke() {return addictiveGrade;}
}
