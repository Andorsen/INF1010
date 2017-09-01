/**
 * Created by Eddy on 01.06.2017.
 * "Antagonist" actors
 */
class Bug extends Entity
{
    Bug(double xCoord, double yCoord)
    {
        x = xCoord;
        y = yCoord;
        Engine.addActorEntity(this);
        image = bugImage;
    }

    double[] smash()
    {
        return new double[]{x, y};
    }
}
