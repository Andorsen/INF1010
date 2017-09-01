import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Eddy on 28.05.2017.
 * Main Game engine
 */
class Engine
{
    private boolean debugMode, bugTracker, showPathToBug;
    private Maze maze;
    private FeatureQuest gui;
    private static LinkedList<Entity> actorEntities;
    private static LinkedList<Entity> worldEntities;
    private static LinkedList<Entity> pathfinderEntities;
    private ArrayList<String> features;
    private FeatureCreep creeper;

    Engine(FeatureQuest parentGui)
    {
        debugMode = true;
        bugTracker = false;
        showPathToBug = false;
        gui = parentGui;
        actorEntities = new LinkedList<>();
        worldEntities = new LinkedList<>();
        pathfinderEntities = new LinkedList<>();
        features = new ArrayList<>();

        creeper = new FeatureCreep(1, 1);
        maze = new Maze();

        tick();
    }

    private void tick()
    {
        gui.centerOn(creeper);
        gui.clearWorld();
        gui.clearActor();
        gui.clearPathfinder();
        for(Entity e: worldEntities) gui.drawWorld(e);
        for(Entity e: actorEntities) gui.drawActor(e);
        for(Entity e: pathfinderEntities) gui.drawPath(e);
        if(bugTracker) findNearestBug();
    }

    void handleUserAction(String key)
    {
        if(debugMode) System.out.println("key pressed:" + key);

        switch(key)
        {
            case ("D"):
                if(legalMove(creeper.getX()+40, creeper.getY()))
                {
                    creeper.modX(40.0);
                }
                break;
            case ("A"):
                if(legalMove(creeper.getX()-40, creeper.getY()))
                {
                    creeper.modX(-40.0);
                }
                break;
            case ("W"):
                if(legalMove(creeper.getX(), creeper.getY()-40))
                {
                    creeper.modY(-40.0);
                }
                break;
            case ("S"):
                if(legalMove(creeper.getX(), creeper.getY()+40))
                {
                    creeper.modY(40.0);
                }
                break;
            case ("ESCAPE"):
                gui.closeGame();
                break;
            case ("NUMPAD7"):
                debugMode = !debugMode;
                System.out.println("Setting debug: "+debugMode);
                break;
            case ("NUMPAD5"):
                bugTracker = true;
                System.out.println("Bug Tracker enabled");
                break;
            case ("NUMPAD9"):
                showPathToBug = true;
                System.out.println("Easy Mode On");
                break;
        }
        if(debugMode)
        {
            creeper.debug();
            System.out.println("Remaining Bugs: "+(actorEntities.size()-1));
        }
        tick();
    }

    private boolean legalMove(double x, double y)
    {
        Tile target = maze.tileAt((int)x/40, (int)y/40);
        if(target.isFeature())
        {
            unlockFeature(target);
            return false;
        }else if(target.isBug())
        {
            smashBug(target.getTheBug());
            return false;
        }else if(target.isPenetratable())
        {
            tryPenetrate(target);
            return false;
        }else if(target.isRefactorable())
        {
            tryRefactor(target);
            return false;
        }
        return target.isFloor();
    }

    private void unlockFeature(Tile t)
    {
        String theNewFeature = t.getFeatureType();
        if(theNewFeature.equals("Extra Ram")) gui.expandView();
        features.add(theNewFeature);
        UnlockedFeatures.newFeaturePopUp(t);
        t.setType('.');
    }

    private void smashBug(Bug bug)
    {
        if(features.contains("Debug Tool"))
        {
            double[] coords = bug.smash();
            double x = coords[0]/40;
            double y = coords[1]/40;
            actorEntities.remove(bug);
            Tile t = maze.tileAt((int)x, (int)y);
            t.rollLoot();
        }
    }

    private void tryPenetrate(Tile t)
    {
        if(features.contains("PenTest"))
        {
            features.remove("PenTest");
            t.penetrate();
        }else
        {
            UnlockedFeatures.noPenTesters();
        }
    }

    private void tryRefactor(Tile t)
    {
        if(features.contains("Analyzer"))
        {
            features.remove("Analyzer");
            t.refactor();
        }else
        {
            UnlockedFeatures.noAnalyzers();
        }
    }

    private void findNearestBug()
    {
        pathfinderEntities.clear();
        if(debugMode) System.out.println("Finding nearest bug");
        Tile origin = maze.tileAt((int)creeper.getX()/40, (int)creeper.getY()/40);
        Tile next = origin;
        Tile temp;
        LinkedList<Tile> q = new LinkedList<>();
        LinkedList<Tile> visited = new LinkedList<>();
        q.clear();
        visited.clear();
        q.offer(origin);
        while(!q.isEmpty())
        {
            next = q.poll();
            visited.add(next);
            if(next.isBug()) break;

            temp = maze.tileAt(next.arrX+1, next.arrY);
            if((temp != null && !visited.contains(temp)) && !q.contains(temp))
            {
                if(temp.isFloor())
                {
                    q.offer(temp);
                    temp.setTrackFrom(next);
                }
            }

            temp = maze.tileAt(next.arrX-1, next.arrY);
            if((temp != null && !visited.contains(temp)) && !q.contains(temp))
            {
                if (temp.isFloor())
                {
                    q.offer(temp);
                    temp.setTrackFrom(next);
                }
            }


            temp = maze.tileAt(next.arrX, next.arrY+1);
            if((temp != null && !visited.contains(temp)) && !q.contains(temp))
            {
                if(temp.isFloor())
                {
                    q.offer(temp);
                    temp.setTrackFrom(next);
                }

            }

            temp = maze.tileAt(next.arrX, next.arrY-1);
            if((temp != null && !visited.contains(temp)) && !q.contains(temp))
            {
                if(temp.isFloor())
                {
                    q.offer(temp);
                    temp.setTrackFrom(next);
                }
            }
        }
        double targetX = next.getX();
        double targetY = next.getY();
        if(debugMode) System.out.printf("Targeting bug: X%f Y%f\n",targetX, targetY);
        gui.showTarget(targetX, targetY, creeper.getX(), creeper.getY());
        if(showPathToBug)
        {
            while(next != null)
            {
                if(debugMode) System.out.printf("Tracing Path X%d.Y%d%n", next.arrX, next.arrY);
                next = next.setToPath();
            }
        }
    }

    static void addActorEntity(Entity entity){actorEntities.add(entity);}
    static void addWorldEntity(Entity entity){worldEntities.add(entity);}
    static void addPathfinderEntity(Entity entity){pathfinderEntities.add(entity);}
}
