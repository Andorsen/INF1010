import java.util.ArrayList;
import customLists.*;

abstract class Tile
{
  protected int xCoord;
  protected int yCoord;
  protected Tile[][] map;
  protected Tile north, south, east, west;
  protected ArrayList<Tile> children;
  protected int cost;
  protected Tile caller;

  protected Tile(int x, int y, Tile[][] pMap)
  {
    xCoord = x;
    yCoord = y;
    map = pMap;
    children = new ArrayList<>();
    caller = null;
  }

  public void explore(LenkeListe<Tile> q)
  {
    Gui.setCurrent(this);
    try{Thread.sleep(10);}catch(InterruptedException e){System.out.println("Oops?");}
    for(Tile t: children)
    {
      if(t.getCost()>cost+1)
      {
        t.setCost(cost+1);
        t.setCaller(this);
        q.add(t);
      }

    }
    if(this instanceof ExitTile) tracePath("", 1);
    else
    {
      Gui.setVisited(this);
      while(q.size()>0)
      {
        q.fjern().explore(q);
      }
    }
  }

  public static Tile create(char c, int x, int y, Tile[][] pMap)
  {
    if(c=='#') return new BlackTile(x, y, pMap);
    else if(isExit(x, y, pMap)) return new ExitTile(x, y, pMap);
    else return new WhiteTile(x, y, pMap);
  }

  private static boolean isExit(int x, int y, Tile[][] pMap)
  {
    if(x==0 || y==0) return true;
    if((x==pMap[y].length-1) || (y==pMap.length-1)) return true;
    return false;
  }

  public void link()
  {
    if(yCoord > 0)
    {
      north = map[yCoord-1][xCoord];
      if(north instanceof WhiteTile) children.add(north);
    }
    if(yCoord < map.length-1)
    {
      south = map[yCoord+1][xCoord];
      if(south instanceof WhiteTile) children.add(south);
    }
    if(xCoord < map[yCoord].length-1)
    {
      east = map[yCoord][xCoord+1];
      if(east instanceof WhiteTile) children.add(east);
    }
    if(xCoord > 0)
    {
      west = map[yCoord][xCoord-1];
      if(west instanceof WhiteTile) children.add(west);
    }
  }
  protected void tracePath(String s, int length)
  {
    s = (xCoord+"."+yCoord+" - "+s);
    try{Thread.sleep(2);}catch(InterruptedException e){System.out.println("Oops");}
    Gui.setPath(this);
    //cost = Integer.MAX_VALUE;
    if(caller!=null) caller.tracePath(s, length+1);
    else
    {
      Gui.incrementExits();
      //System.out.println(s);
      System.out.println("Path length: "+length);
    }
  }

  public void setCaller(Tile t){caller = t;}
  public int getCost(){return cost;}
  public void setCost(int i){cost = i;}

}
