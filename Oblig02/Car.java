class Car extends InternalCombustion
{
  private int seats;

  public Car(String newRegistryID, double newEmission, int newSeats)
  {
    registryID = newRegistryID;
    emission = newEmission;
    seats = newSeats;
  }
  public int getSeats()
  {
    return seats;
  }
  public void printInfo()
  {
    super.printInfo();
    System.out.println("Number of seats: "+seats+"\n");
  }
}
