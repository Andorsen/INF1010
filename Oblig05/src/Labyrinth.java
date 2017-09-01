import java.util.*;
import java.io.*;

class Labyrinth
{
  private Tile[][] map;
  int mapHeight, mapWidth;
  Tile startTile;

  private Labyrinth(String fileName)
  {
    setup(fileName);
  }

  public static Labyrinth createFromFile(String fileName)
  {
    return new Labyrinth(fileName);
  }

  private void setup(String fileName)
  {
    String line;
    try
    {
      Scanner in = new Scanner(new File(fileName));
      line = in.nextLine();
      createMap(line);
      createTiles(in);
      //forceBoard();
      linkTiles();
    }catch(FileNotFoundException e)
    {
      System.out.printf("File \"%s\" not found%n", fileName);
    }
  }

  private void createMap(String line)
  {
    String[] dimentions = line.split(" ");
    mapHeight = Integer.parseInt(dimentions[0]);
    mapWidth = Integer.parseInt(dimentions[1]);
    map = new Tile[mapHeight][mapWidth];
  }

  private void createTiles(Scanner in)
  {
    String line;
    for(int y = 0; y<map.length; y++)
    {
      line = in.nextLine();
      for(int x = 0; x<line.length(); x++)
      {
        map[y][x] = Tile.create(line.charAt(x), x, y, map);
      }
    }
  }

  private void linkTiles()
  {
    for(int y = 0; y<map.length; y++)
    {
      for(int x = 0; x<map[y].length; x++)
      {
        map[y][x].link();
      }
    }
  }

  private void forceBoard()
  {
    Tile t;
    char c;
    for(int y = 0; y<map.length; y++)
    {
      for(int x = 0; x<map[y].length; x++)
      {
        t = map[y][x];
        if(t instanceof BlackTile) c = '#';
        else c = '.';
        if(t instanceof ExitTile) c = 'O';
        System.out.print(c);
      }
      System.out.println("");
    }
  }

  public void setStartTile(int x, int y)
  {
    startTile = map[y][x];
  }

  public void resetCosts()
  {
    for(int y = 0; y<map.length; y++)
    {
      for(int x = 0; x<map[y].length; x++)
      {
        map[y][x].setCost(Integer.MAX_VALUE);
      }
    }
  }

  public Tile[][] getMap() {return map;}
  public Tile getStartTile() {return startTile;}
}
