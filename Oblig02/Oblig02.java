import java.util.*;
import java.io.*;

class Oblig02
{
  static String filename;
  static ArrayList<Vehicle> vehicles = new ArrayList<>();
  static boolean showInternalc = true;
  static boolean showElectric = true;

  public static void main(String[] args)
  {
    if(args.length>1)
    {
      if(args[1].equals("EL")) showInternalc = false;
      if(args[1].equals("FOSSIL")) showElectric = false;
    }
    filename = args[0];
    try
    {
      Scanner fromFile = new Scanner(new File(filename));
      loadArchive(fromFile);

    }catch(FileNotFoundException e)
    {
      System.out.printf("File: %s not found", filename);
    }

    for(Vehicle v: vehicles)
    {
      if((v instanceof Electric && showElectric) || (v instanceof InternalCombustion && showInternalc))
      {
      v.printInfo();
      }
    }
  }

  static void loadArchive(Scanner in)
  {
    String line;
    while(in.hasNext())
    {
      line = in.nextLine();
      String[] tok = line.split(" ");
      if(tok[0].equals("EL")) vehicles.add(new Electric(tok[1], Double.parseDouble(tok[2])));
      else if(tok[0].equals("LASTEBIL")) vehicles.add(new Lorry(tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3])));
      else if(tok[0].equals("PERSONBIL")) vehicles.add(new Car(tok[1], Double.parseDouble(tok[2]), Integer.parseInt(tok[3])));
    }
  }
}
