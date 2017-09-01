class Lorry extends InternalCombustion
{
  private double carryLoad;
  public Lorry(String newRegistryID, double newEmission, double newCarryLoad)
  {
    registryID = newRegistryID;
    emission = newEmission;
    carryLoad = newCarryLoad;
  }

  public double getCarryLoad()
  {
    return carryLoad;
  }

  public void printInfo()
  {
    super.printInfo();
    System.out.println("Carry capacity: "+carryLoad+"\n");
  }
}
