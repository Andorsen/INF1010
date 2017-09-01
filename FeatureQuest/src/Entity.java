import javafx.scene.image.Image;

/**
 * Created by Eddy on 26.05.2017.
 * Abstract class for all game entities
 */
abstract class Entity
{
    static Image defaultImage = new Image("images/placeHolder.bmp", 40, 40, true, false);
    static Image wallImage = new Image("images/wall.bmp", 40, 40, true, false);
    static Image floorImage = new Image("images/floor.bmp", 40, 40, true, false);
    static Image creeperImage = new Image("images/creep.bmp", 40, 40, true, false);
    static Image dToolImage = new Image("images/dTool.bmp", 40, 40, true, false);
    static Image analyzerImage = new Image("images/analyzer.bmp", 40, 40, true, false);
    static Image extraRamImage = new Image("images/extraRam.bmp", 40, 40, true, false);
    static Image penTestImage = new Image("images/penTest.bmp", 40, 40, true, false);
    static Image bugImage = new Image("images/bug.bmp", 40,40,true, false);
    static Image badWallImage = new Image("images/badWall.bmp", 40, 40, true, false);
    static Image spaghettiWallImage = new Image("images/spaghettiWall.bmp", 40, 40, true, false);
    static Image pathImage = new Image("images/floorPath.bmp", 20, 20, true, false);
    protected Image image;
    protected double x,y;

    protected Entity(double xCoord, double yCoord)
    {
        x = xCoord*40.0;
        y = yCoord*40.0;
    }
    protected Entity(){}

    double getX(){return x;}
    double getY(){return y;}
    void modX(double xCoordMod){x += xCoordMod;}
    void modY(double yCoordMod){y += yCoordMod;}
    Image getImage(){return image != null ? image : defaultImage;}
}
