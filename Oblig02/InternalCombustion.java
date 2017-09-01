abstract class InternalCombustion extends Vehicle
{
  protected double emission;

  public double getEmission()
  {
    return emission;
  }

  public void printInfo()
  {
    super.printInfo();
    System.out.println("CO2 Emission: "+emission);
  }
}
