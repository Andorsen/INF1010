import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by Eddy on 26.05.2017.
 * FeatureQuest the Game
 */
public class FeatureQuest extends Application
{
    //GUI related fields
    private static Stage stage;
    private static Scene scene;
    private static Group root;
    private static Canvas worldCanvas, actorCanvas, pathfinderCanvas;
    private static GraphicsContext worldGC, actorGC, pathfinderGC;
    private static double xMod, yMod;
    private static Engine engine;


    public static void main(String[] args)
    {
        System.out.println(System.getProperty("user.dir"));
        Thread t = Thread.currentThread();
        System.out.println(t.getName());
        launch(args);
    }

    public void start(Stage primaryStage)
    {

        stage = primaryStage;
        initialSetup();
        gameSetup();
        stage.show();
    }

    private void initialSetup()
    {
        root = new Group();
        worldCanvas = new Canvas(5000, 5000);
        actorCanvas = new Canvas();
        pathfinderCanvas = new Canvas();
        scene = new Scene(root,960, 540, Color.BLACK);
        worldGC = worldCanvas.getGraphicsContext2D();
        actorGC = actorCanvas.getGraphicsContext2D();
        pathfinderGC = pathfinderCanvas.getGraphicsContext2D();

        root.getChildren().add(worldCanvas);
        root.getChildren().add(actorCanvas);
        root.getChildren().add(pathfinderCanvas);
        pathfinderCanvas.toFront();
        actorCanvas.toFront();
        stage.setScene(scene);
        stage.setTitle("Feature Quest");
        stage.setWidth(640);
        stage.setHeight(360);
        actorCanvas.setWidth(640);
        actorCanvas.setHeight(360);
        pathfinderCanvas.setWidth(640);
        pathfinderCanvas.setHeight(360);
        stage.setResizable(false);


        xMod = 0.0;
        yMod = 0.0;
    }

    private void gameSetup()
    {

        engine = new Engine(this);
        scene.setOnKeyReleased(e -> engine.handleUserAction(e.getCode().name()));
    }

    void drawWorld(Entity e)
    {
        double x = e.getX()+xMod;
        double y = e.getY()+yMod;
        worldGC.drawImage(e.getImage(), x, y);
    }

    void drawActor(Entity e)
    {
        double x = e.getX()+xMod;
        double y = e.getY()+yMod;
        actorGC.drawImage(e.getImage(), x, y);
    }

    void drawPath(Entity e)
    {
        double x = e.getX()+xMod+10;
        double y = e.getY()+yMod+10;
        pathfinderGC.drawImage(e.getImage(), x, y);
    }

    void expandView()
    {
        stage.setWidth(stage.getWidth()+40);
        stage.setHeight(stage.getHeight()+40);
        actorCanvas.setWidth(stage.getWidth());
        actorCanvas.setHeight(stage.getHeight());
        pathfinderCanvas.setHeight(stage.getHeight());
        pathfinderCanvas.setWidth(stage.getWidth());
    }

    void showTarget(double targetX, double targetY, double originX, double originY)
    {
        actorGC.setStroke(Color.DARKBLUE);
        actorGC.setLineWidth(2.0);
        actorGC.strokeLine(originX+xMod+20, originY+yMod+20, targetX+xMod+20, targetY+yMod+20);
    }

    void clearActor(){actorGC.clearRect(0,0,actorCanvas.getWidth(),actorCanvas.getHeight());}
    void clearWorld(){worldGC.clearRect(0,0,worldCanvas.getWidth(),worldCanvas.getHeight());}
    void clearPathfinder(){pathfinderGC.clearRect(0, 0, pathfinderCanvas.getWidth(), pathfinderCanvas.getHeight());}

    void closeGame(){stage.close();}

    void centerOn(Entity entity)
    {
        xMod = actorCanvas.getWidth()/2-entity.getX();
        yMod = actorCanvas.getHeight()/2-entity.getY();
    }
}
