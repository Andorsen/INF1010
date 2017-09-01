import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Eddy on 26.05.2017.
 * The Game World
 */
class Maze
{
    Tile[][] map;
    Tile current;

    private int height, width;

    Maze()
    {
        setup();
    }

    private void setup()
    {
        try
        {
            Scanner in = new Scanner(new File("maps/map135x151.txt"));
            String line = in.nextLine();
            createMap(line);
            createTiles(in);
            current = map[1][1];
        }catch(FileNotFoundException e)
        {
            System.out.println("Error during Maze setup: File Not Found");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createMap(String line)
    {
        String[] dimensions = line.split(" ");
        height = Integer.parseInt(dimensions[0]);
        width = Integer.parseInt(dimensions[1]);
        map = new Tile[height][width];
    }

    private void createTiles(Scanner in)
    {
        String line;
        for(int y = 0; y<height; y++)
        {
            line = in.nextLine();
            for(int x = 0; x<width; x++)
            {
                map[y][x] = new Tile(line.charAt(x), x, y);
            }
        }
    }

    Tile[][] getMap(){return map;}
    int getHeight(){return height;}
    int getWidth(){return width;}
    Tile tileAt(int x, int y)
    {
        if((x<0 || y<0)||(x>=width || y>=height)) return null;
        else return map[y][x];
    }
}
