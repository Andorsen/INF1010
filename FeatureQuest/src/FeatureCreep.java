import javafx.scene.image.Image;

/**
 * Created by Eddy on 26.05.2017.
 * The player character, the Feature Creep
 */
class FeatureCreep extends Entity
{
    FeatureCreep(double xCoord, double yCoord)
    {
        super(xCoord, yCoord);
        Engine.addActorEntity(this);
        image = creeperImage;
    }

    void debug()
    {
        System.out.println("X: "+x+" Y:"+y);
    }
}
