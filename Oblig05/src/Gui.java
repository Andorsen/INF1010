import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import java.util.HashMap;

public class Gui extends Application
{
  static Image exitImage, floorPathImage, floorVisitedImage, floorImage;
  static Image startImage, wallImage, floorCurrentImage;
  static Image floorPath2Image, floorPath3Image;
  static Labyrinth lab;
  GridPane layout;
  Scene scene;
  Button breadth, width;
  static Tile[][] map;
  static HashMap<Tile, ImageView> tileImages;
  static int scale;
  static int exits;
  Thread solverThread;
  Solver solver;

  public Gui()
  {
  }

  static void launchGui(Labyrinth pLab)
  {
    lab = pLab;
    map = lab.getMap();
    launch();
  }

  public void start(Stage primaryStage)
  {
    setScale();
    exits = 0;
    tileImages = new HashMap<>();
    exitImage = new Image("images/exit.bmp", scale, scale, true, false);
    floorPathImage = new Image("images/floor-path.bmp", scale, scale, true, false);
    floorPath2Image = new Image("images/floor-path2.bmp", scale, scale, true, false);
    floorPath3Image = new Image("images/floor-path3.bmp", scale, scale, true, false);
    floorVisitedImage = new Image("images/floor-visited.bmp", scale, scale, true, false);
    floorImage = new Image("images/floor.bmp", scale, scale, true, false);
    startImage = new Image("images/start.bmp", scale, scale, true, false);
    wallImage = new Image("images/wall.bmp", scale, scale, true, false);
    floorCurrentImage = new Image("images/floorCurrent.bmp", scale, scale, true, false);
    layout = new GridPane();
    scene = new Scene(layout);

    addTilesToLayout();

    breadth = new Button();
    breadth.setText("Breadth Search");
    breadth.setOnAction(e -> solveBreadth());
    layout.add(breadth, 0, map.length, 30, 20);
    width = new Button();
    width.setText("Depth Search");
    width.setOnAction(e -> solveDepth());
    layout.add(width, 0, map.length+20, 30, 20);

    solver = new Solver(lab);
    solverThread = new Thread(solver);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void addTilesToLayout()
  {
    Tile t;
    ImageView iv;
    for(int y = 0; y<map.length; y++)
    {
      for(int x = 0; x<map[y].length; x++)
      {
        t = map[y][x];
        iv = new ImageView();
        if(t instanceof ExitTile) iv.setImage(exitImage);
        else if(t instanceof BlackTile) iv.setImage(wallImage);
        else iv.setImage(floorImage);
        tileImages.put(t, iv);
        layout.add(iv, x, y);

      }
    }
  }

  private void setScale()
  {
    if(map.length>100) scale = 7;
    else if(map.length>70) scale = 10;
    else if(map.length>50) scale = 15;
    else scale = 20;
  }

  private void resetImages()
  {
    for(Tile[] ta: map)
    {
      for(Tile t: ta)
      {
        tileImages.get(t)
                  .setImage(wallImage);
      }
    }
  }

  private void solveDepth()
  {
    resetImages();
    solver.setType(0);
    Thread t = new Thread(solver);
    t.start();
  }

  private void solveBreadth()
  {
    resetImages();
    solver.setType(1);
    Thread t = new Thread(solver);
    t.start();
  }

  static void setVisited(Tile t)
  {
    tileImages.get(t).setImage(floorVisitedImage);
  }
  static void setPath(Tile t)
  {
    if(exits==0) tileImages.get(t).setImage(floorPathImage);
    else if(exits==1) tileImages.get(t).setImage(floorPath2Image);
    else if(exits==2) tileImages.get(t).setImage(floorPath3Image);
  }
  static void setCurrent(Tile t)
  {
    tileImages.get(t).setImage(floorCurrentImage);
  }
  static void setStart(Tile t)
  {
    tileImages.get(t).setImage(startImage);
  }
  static void incrementExits()
  {
    exits++;
    if(exits>2) exits = 0;
  }
}
