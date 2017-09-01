import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


class Maze implements Runnable
{
    private File mazeFile;
    private int height, width;
    private Tile[][] map;
    private Tile startTile, currentTile;
    private static ArrayList<Tile> exits = new ArrayList<>();
    private boolean flashExitPaths = true;

    Maze(File pMazeFile)
    {
        mazeFile = pMazeFile;
        setup();
    }

    public void run()
    {
        solve();
    }

    private void setup()
    {
        System.out.println("Setting up maze");
        try
        {
            Scanner in = new Scanner(mazeFile);
            String line = in.nextLine();
            createMap(line);
            createTiles(in);
            linkTiles();
        }catch(FileNotFoundException e)
        {
            System.out.println("File not Found");
            e.printStackTrace();
        }
    }

    private void createMap(String line)
    {
        String[] dimensions = line.split(" ");
        height = Integer.parseInt(dimensions[0]);
        width = Integer.parseInt(dimensions[1]);
        map = new Tile[height][width];
    }

    private void solve()
    {
        LinkedList<Tile> q = new LinkedList<>();
        q.offer(startTile);

        while(q.size()>0)
        {
            q.poll().addChildrenToQueue(q);
            //try{Thread.sleep(1);}catch(InterruptedException e){}
        }

        while(flashExitPaths)
        {
            //try {Thread.sleep(1000);}catch(InterruptedException e){};
            hideAll();
            for(Tile tile: exits)
            {
                if(flashExitPaths)
                {
                    Oblig07.updatePathColor();
                    tile.traceExitPath();
                }
            }
        }

    }


    private void createTiles(Scanner in)
    {
        String line;
        for(int y = 0; y<height; y++)
        {
            line = in.nextLine();
            for(int x = 0; x<line.length(); x++)
            {
                map[y][x] = new Tile(line.charAt(x), x, y, map);
            }
        }
    }

    private void linkTiles()
    {
        for(int y = 0; y<height; y++)
        {
            for(int x = 0; x<width; x++)
            {
                map[y][x].link();
            }
        }
    }

    boolean setStart(int x, int y)
    {
        Tile t = map[y][x];
        if(t.isFloor())
        {
            if(startTile != null) startTile.setFloor();
            t.setStart();
            startTile = t;
            currentTile = startTile;
            return true;
        }else return false;
    }

    void goDirection(String direction)
    {
        currentTile = currentTile.goDirection(direction);
    }

    void hideAll()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                //System.out.printf("hiding tile %d,%d%n", x, y);
                Oblig07.setHiddenImage(map[y][x]);
                //try{Thread.sleep(1);}catch(InterruptedException e){}
            }
        }
    }


    int getHeight(){return height;}
    int getWidth(){return width;}
    Tile getStartTile(){return startTile;}
    static void addExit(Tile tile){exits.add(tile);}
    void haltFlashingPaths(){flashExitPaths = false;}

}
