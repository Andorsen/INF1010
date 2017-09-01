class Node
{
  private static int lastNodeID = 0;
  private int nodeID;
  private int ram;
  private int cpu;
  private int cores;
  private double speed;
  private int flopsPerCycle = 8;

  public Node(int a, int b, int c, double d)
  {
    nodeID = lastNodeID+1;
    lastNodeID = nodeID;
    ram = a;
    cpu = b;
    cores = c;
    speed = d*1000000000;
  }

  public double getNodeFlops()
  {
    return (1*cpu*cores*speed*flopsPerCycle);
  }

  public int getMemory()
  {
    return ram;
  }
}
