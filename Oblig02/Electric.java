class Electric extends Vehicle
{
  private double batteryCapacity;

  public Electric(String newRegistryID, double newMaxBatteryCapacity)
  {
    registryID = newRegistryID;
    batteryCapacity = newMaxBatteryCapacity;
  }

  public double getBatteryCapacity()
  {
    return batteryCapacity;
  }

  public void printInfo()
  {
    super.printInfo();
    System.out.println("Battery capacity: "+batteryCapacity+"\n");
  }
}
