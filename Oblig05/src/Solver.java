import customLists.*;

class Solver implements Runnable
{
  Labyrinth lab;
  Tile startTile;
  int type;

  public Solver(Labyrinth pLab)
  {
    lab = pLab;
    startTile = lab.getStartTile();
    type = 1;
  }

  public void run()
  {
    lab.resetCosts();
    try{Thread.sleep(1000);}catch(InterruptedException e){}
    if(type == 1) breadth();
    else depth();
    Gui.setStart(startTile);
    System.out.println("Search done");
  }

  private void breadth()
  {
    System.out.println("Starting Breadth-First Search");
    Queue<Tile> q = new Queue<>();
    q.add(startTile);
    startTile.setCost(0);
    startTile.explore(q);


  }

  private void depth()
  {
    System.out.println("Starting Depth-First Search");
    Stabel<Tile> q = new Stabel<>();
    q.add(startTile);
    startTile.setCost(0);
    startTile.explore(q);
/*
    while(q.size()>0)
    {
    //  q.fjern().explore(q);
  }*/
  }

  public void setType(int i)
  {
    type = i;
  }
}
