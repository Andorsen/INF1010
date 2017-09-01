class Pathfinder
{
  static Labyrinth lab;
  static String fileName = "mazes/map135x151.txt";

  public static void main(String[] args)
  {
    lab = Labyrinth.createFromFile(fileName);
    Tile[][] map = lab.getMap();
    lab.setStartTile(1, map.length-2);
    Gui.launchGui(lab);

    /*
    Solver solver = new Solver(lab);
    Thread t = new Thread(solver);
    solver.setType(0);
    t.start();
    */

  }
}
