abstract class Vehicle
{
  protected String registryID;

  public String getRegistryID()
  {
    return registryID;
  }

  public void printInfo()
  {
    System.out.println("Type: "+this.getClass().getName());
    System.out.println("Registry ID: "+registryID);
  }
}
