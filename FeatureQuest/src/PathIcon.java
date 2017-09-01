/**
 * Created by Eddy on 02.06.2017.
 */
public class PathIcon extends Entity
{
    PathIcon(int xCoord, int yCoord)
    {
        super(xCoord, yCoord);
        image = pathImage;
        Engine.addPathfinderEntity(this);
    }
}
