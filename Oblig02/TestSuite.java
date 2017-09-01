class TestSuite
{
  
  public static void main(String[] args)
  {
    String elReg = "EL 1337460";
    double elCap = 100.0;
    Electric e = new Electric(elReg, elCap);
    testEl(e, elReg, elCap);

    String carReg = "AB 12345678";
    double carEmission = 0.44;
    int carSeats = 5;
    Car c = new Car(carReg, carEmission, carSeats);
    testCar(c, carReg, carEmission, carSeats);

    String lorReg = "LR 4444444";
    double lorEm = 1.2;
    double load = 8000.0;
    Lorry l = new Lorry(lorReg, lorEm, load);
    testLorry(l, lorReg, lorEm, load);

  }
  public static void testEl(Electric e, String elReg, double elCap)
  {
    System.out.println("\nElectric Vehicle:");
    System.out.print("Registry: ");
    if(e.getRegistryID().equals(elReg)) System.out.println("PASS");
    else System.out.printf("FAIL - Expected %s, found %s\n", elReg, e.getRegistryID());
    System.out.print("Battery Capacity: ");
    if(e.getBatteryCapacity()==elCap) System.out.println("PASS");
    else System.out.printf("FAIL - Expected %s, found %s\n", elCap, e.getBatteryCapacity());
  }

  public static void testCar(Car c, String reg, double emission, int seats)
  {
    System.out.println("\nCar: ");
    System.out.print("Registry: ");
    if(c.getRegistryID().equals(reg)) System.out.println("PASS");
    else System.out.printf("FAIL - Expected: %s, Found: %s\n", reg, c.getRegistryID());
    System.out.print("Emission: ");
    if(c.getEmission()==emission) System.out.println("PASS");
    else System.out.printf("FAIL - Expected %s, Found: %s\n", emission, c.getEmission());
    System.out.print("Seats: ");
    if(c.getSeats()==seats) System.out.println("PASS");
    else System.out.printf("FAIL - Expected: %s, Found: %s\n", seats, c.getSeats());
  }

  public static void testLorry(Lorry l, String reg, double emission, double load)
  {
    System.out.println("\nLorry: ");
    System.out.print("Registry: ");
    if(l.getRegistryID().equals(reg)) System.out.println("PASS");
    else System.out.printf("FAIL - Expected: %s, Found: %s\n", reg, l.getRegistryID());
    System.out.print("Emission: ");
    if(l.getEmission()==emission) System.out.println("PASS");
    else System.out.printf("FAIL - Expected %s, Found: %s\n", emission, l.getEmission());
    System.out.print("Carry Weight: ");
    if(l.getCarryLoad()==load) System.out.println("PASS");
    else System.out.printf("FAIL - Expected: %s, Found: %s\n", load, l.getCarryLoad());
  }
}
