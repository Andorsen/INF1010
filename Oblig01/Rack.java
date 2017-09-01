class Rack
{
  private int nodesPerRack;
  private Node[] nodes;
  private int nextNodePos = 0;

  public Rack(int capacity)
  {
    nodesPerRack = capacity;
    nodes = new Node[capacity];
  }

  /**
  / @Return True if Rack has available slot for new node
  */
  public boolean hasAvailableSlot()
  {
    return nextNodePos<(nodes.length) ? true : false;
  }

  /**
  /
  */
  public void mount(Node n)
  {
    nodes[nextNodePos++] = n;
  }

  public int nodesMinGB(int n)
  {
    int validNodes = 0;
    for(int i = 0; i<nextNodePos; i++)
    {
      if(nodes[i].getMemory()>=n) validNodes++;
    }
    return validNodes;
  }

  public double getRackFlops()
  {
    double rackFlops = 0;
    for(int i = 0; i<nextNodePos; i++)
    {
      rackFlops += nodes[i].getNodeFlops();
    }
    return rackFlops;
  }
}
