import java.util.Queue;

/**
 * Created by Eddy on 26.05.2017.
 * The individual Tiles contained in the Maze
 */
class Tile extends Entity
{
    char c;
    private boolean wall, floor, feature, bug, refactorable, penetratable;
    private String featureType;
    private Bug theBug;
    int arrX, arrY;
    Tile trackFrom;

    public Tile(char characterSymbol, int xCoord, int yCoord)
    {
        super(xCoord, yCoord);
        arrX = xCoord;
        arrY = yCoord;
        Engine.addWorldEntity(this);
        setType(characterSymbol);
        mabyBug();
    }

    void setType(char characterSymbol)
    {
        c = characterSymbol;
        wall = false;
        floor = false;
        feature = false;
        featureType = null;
        bug = false;
        refactorable = false;
        penetratable = false;

        if(c=='#')
        {
            double rng = Math.random();
            if(rng>0.95)
            {
                image = badWallImage;
                penetratable = true;
            }else if(rng>0.90)
            {
                image = spaghettiWallImage;
                refactorable = true;
            }else
            {
                image = wallImage;
            }
            wall = true;
        }
        else if(c=='.')
        {
            image = floorImage;
            floor = true;
        }else if(c=='A')
        {
            image = dToolImage;
            floor = true;
            feature = true;
            featureType = "Debug Tool";
        }else if(c=='B')
        {
            image = analyzerImage;
            floor = true;
            feature = true;
            featureType = "Analyzer";
        }else if(c=='C')
        {
            image = extraRamImage;
            floor = true;
            feature = true;
            featureType = "Extra Ram";
        }else if(c=='D')
        {
            image = penTestImage;
            floor = true;
            feature = true;
            featureType = "PenTest";
        }
    }

    private void mabyBug()
    {
        if(c=='.')
        {
            if(Math.random()>0.97)
            {
                theBug = new Bug(x, y);
                bug = true;
            }
        }
    }

    void rollLoot()
    {
        double rng = Math.random();
        if(rng<0.2) setType('D');
        else if(rng<0.4) setType('C');
        else if(rng<0.6) setType('B');
        else setType('.');
    }

    boolean isWall(){return wall;}
    boolean isFloor(){return floor;}

    boolean isFeature(){return feature;}
    String getFeatureType(){return featureType;}

    boolean isBug(){return bug;}
    Bug getTheBug(){return theBug;}
    
    boolean isRefactorable(){return refactorable;}
    void refactor(){rollLoot();}

    boolean isPenetratable(){return penetratable;}
    void penetrate(){rollLoot();}

    void setTrackFrom(Tile t){trackFrom = t;}
    Tile setToPath()
    {
        new PathIcon(arrX, arrY);
        if(trackFrom != null)
        {
            Tile t = trackFrom;
            trackFrom = null;
            return t;
        }
        return null;
    }
}