import java.util.ArrayList;
import java.util.LinkedList;

class Tile
{
    private char c;
    private int x, y;
    private boolean wallTile, exitTile, floorTile, explored, visited;
    private Tile[][] map;
    private ArrayList<Tile> children = new ArrayList<>();
    private Tile parent;


    Tile(char symbol, int xCoord, int yCoord, Tile[][] tileMap)
    {
        c = symbol;
        x = xCoord;
        y = yCoord;
        map = tileMap;
        wallTile = false;
        exitTile = false;
        floorTile = false;
        explored = false;
        setType();
        Oblig07.addTileToGui(this);
    }

    private void setType()
    {
        if(c=='#')
        {
            wallTile = true;
        }else if(c=='.')
        {
            floorTile = true;
            if(x==0 || y==0 || y==map.length-1 || x==map[y].length-1) exitTile = true;
        }
    }

    void link()
    {
        //if(y<map.length-1 && x<map[y].length-1) children.add(map[y + 1][x + 1]);
        if(y<map.length-1) children.add(map[y + 1][x]);
        //if(y<map.length-1 && x>0) children.add(map[y + 1][x - 1]);
        if(x<map[y].length-1) children.add(map[y][x + 1]);
        if(x>0)  children.add(map[y][x - 1]);
        //if(y>0 && x<map[y].length-1) children.add(map[y - 1][x + 1]);
        if(y>0) children.add(map[y - 1][x]);
        //if(y>0 && x>0)  children.add(map[y - 1][x - 1]);
    }

    void addChildrenToQueue(LinkedList<Tile> q)
    {
        visited = true;
        Oblig07.setVisitedImage(this);
        if(exitTile) Maze.addExit(this);

        for(Tile t: children)
        {
            if(t.isFloor() && !t.isVisited() && !q.contains(t))
            {
                q.offer(t);
                t.setParent(this);
            }
        }
    }

    Tile goDirection(String direction)
    {
        Tile target = this;
        if(direction.equals("w")) target = map[y-1][x];
        if(direction.equals("s")) target = map[y+1][x];
        if(direction.equals("d")) target = map[y][x+1];
        if(direction.equals("a")) target = map[y][x-1];

        if(target.isFloor())
        {
            Oblig07.setFloorImage(this);
            target.setCurrent();
            return target;
        }else return this;
    }

    void showNearby(int sightRange)
    {
        show();
        if (sightRange > 1)
        {
            sightRange--;
            if(y<map.length-1 && x<map[y].length-1) map[y + 1][x + 1].showNearby(sightRange);
            if(y<map.length-1) map[y + 1][x].showNearby(sightRange);
            if(y<map.length-1 && x>0) map[y + 1][x - 1].showNearby(sightRange);
            if(x<map[y].length-1) map[y][x + 1].showNearby(sightRange);
            if(x<0)  map[y][x - 1].showNearby(sightRange);
            if(y>0 && x<map[y].length-1) map[y - 1][x + 1].showNearby(sightRange);
            if(y>0) map[y - 1][x].showNearby(sightRange);
            if(y>0 && x>0)  map[y - 1][x - 1].showNearby(sightRange);
        }
    }

    private void show()
    {
        if(!explored)
        {
            explored = true;
            if (exitTile) Oblig07.setExitImage(this);
            else if (floorTile) Oblig07.setFloorImage(this);
            else if (wallTile) Oblig07.setWallImage(this);
        }
    }

    void traceExitPath()
    {
        try{Thread.sleep(5);}catch(InterruptedException e){};
        if(parent != null)
        {
            Oblig07.setPathImage(this);
            parent.traceExitPath();
        }else Oblig07.setStartImage(this);
    }

    void setStart()
    {
        Oblig07.setStartImage(this);
    }

    void setFloor()
    {
        Oblig07.setFloorImage(this);
    }

    void setParent(Tile tile){parent = tile;}

    void setCurrent()
    {
        if(exitTile) System.out.println("You Wonz!!");
        else
        {
            showNearby(3);
            Oblig07.setDudeImage(this);
        }
    }

    int getXCoord(){return x;}
    int getYCoord(){return y;}
    boolean isWall(){return wallTile;}
    boolean isExit(){return exitTile;}
    boolean isFloor(){return floorTile;}
    boolean isVisited(){return visited;}
}
