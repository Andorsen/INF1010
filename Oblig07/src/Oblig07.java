import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Oblig07 extends Application
{
    private static FileChooser fileChooser;
    private static Stage stage;
    private static GridPane layout;
    private static HBox mainLayout;
    private static Scene scene;
    private static Image wall, floor, exit, start, dude, hidden, visited, path, path1, path2, path3;
    private static File mazeFile;
    private static Maze maze;
    private static int pathColor = 0;
    private static volatile HashMap<Tile, ImageView> tileImages;
    private static ArrayList<Tile> exits = new ArrayList<>();
    private static Thread solverThread;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        initialize();
        stage.show();
    }

    private void initialize()
    {
        tileImages = new HashMap<>();
        hidden = new Image("images/hidden.bmp", 10, 10, true, false);
        dude = new Image("images/dude.bmp", 10, 10, true, false);
        wall = new Image("images/wall.bmp", 10, 10, true, false);
        floor = new Image("images/floor.bmp", 10, 10, true, false);
        exit = new Image("images/exit.bmp", 10, 10, true, false);
        start = new Image("images/start.bmp", 10, 10, true, false);
        visited = new Image("images/floor-visited.bmp", 10, 10, true, false);
        path1 = new Image("images/floor-path.bmp", 10, 10, true, false);
        path2 = new Image("images/floor-path2.bmp", 10, 10, true, false);
        path3 = new Image("images/floor-path3.bmp", 10, 10, true, false);

        stage.setTitle("Maze solver - Oblig07");

        selectMaze();


    }

    private void selectMaze()
    {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Maze");
        mazeFile = fileChooser.showOpenDialog(stage);
        if(mazeFile != null)
        {
            layout = new GridPane();
            maze = new Maze(mazeFile);
            scene = new Scene(layout, maze.getWidth()*10, maze.getHeight()*10);
            stage.setScene(scene);
        }
    }

    static void addTileToGui(Tile tile)
    {
        ImageView iv = new ImageView();
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->
        {
            int x = GridPane.getColumnIndex(iv);
            int y = GridPane.getRowIndex(iv);
            System.out.printf("X: %d - Y: %d%n", x, y);
            if(maze.setStart(x, y))
            {
                System.out.println("Start position accepted");
                solverMode();
            }
            else System.out.println("Invalid Start position");
        });
        if(tile.isWall()) iv.setImage(wall);
        else if(tile.isFloor()) iv.setImage(floor);
        else if(tile.isExit()) iv.setImage(exit);
        tileImages.put(tile, iv);
        layout.add(iv, tile.getXCoord(), tile.getYCoord());
    }

    private static void solverMode()
    {
        solverThread = new Thread(maze);
        stage.setOnCloseRequest(e -> maze.haltFlashingPaths());
        solverThread.start();

        /*
        ***Code for later implementation***
        System.out.println("Starting \"GAME MODE\"");
        maze.hideAll();
        maze.getStartTile().setCurrent();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) ->
        {
            String direction = e.getText();
            maze.goDirection(direction);
        });
        */
    }

    static void updatePathColor()
    {
        if(pathColor>5) pathColor = 0;
        if(pathColor==0) path = path1;
        else if(pathColor == 1) path = path2;
        else if(pathColor == 2) path = path3;
        else if(pathColor == 3) path = visited;
        else if(pathColor == 4) path = floor;
        else if(pathColor == 5) path = wall;
        pathColor++;
    }



    static void setStartImage(Tile tile){tileImages.get(tile).setImage(start);}
    static void setFloorImage(Tile tile){tileImages.get(tile).setImage(floor);}
    static void setDudeImage(Tile tile){tileImages.get(tile).setImage(dude);}
    static void setHiddenImage(Tile tile){tileImages.get(tile).setImage(hidden);}
    static void setExitImage(Tile tile){tileImages.get(tile).setImage(exit);}
    static void setWallImage(Tile tile){tileImages.get(tile).setImage(wall);}
    static void setVisitedImage(Tile tile){tileImages.get(tile).setImage(visited);}
    static void setPathImage(Tile tile){tileImages.get(tile).setImage(path);}
}
