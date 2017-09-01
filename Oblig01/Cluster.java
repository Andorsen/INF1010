import java.util.*;

class Cluster
{
  private int nodesPerRack;
  private ArrayList<Rack> racks = new ArrayList<>();

  public Cluster(int nodes)
  {
    nodesPerRack = nodes<1 ? 1: nodes;
    racks.add(new Rack(nodesPerRack));
  }

  public void addNodes(int quantity, int ram, int cpus, int cores, double speed)
  {
    for(int i = 0; i<quantity; i++)
    {
      mountNode(new Node(ram, cpus, cores, speed));
    }
  }

  private int mountNode(Node n)
  {
    for(Rack r: racks)
    {
      if(r.hasAvailableSlot())
      {
        r.mount(n);
        return 1;
      }
    }
    racks.add(new Rack(nodesPerRack));
    return mountNode(n);
  }

  public double getClusterFlops()
  {
    double clusterFlops = 0;
    for(Rack r: racks)
    {
      clusterFlops += r.getRackFlops();
    }
    return clusterFlops;
  }

  public int getRackCount()
  {
    return racks.size();
  }

  public int nodeCount(int n)
  {
    int minGBmemNodes = 0;
    for(Rack r: racks)
    {
      minGBmemNodes += r.nodesMinGB(n);
    }
    return minGBmemNodes;
  }

  public int getNodesPerRack()
  {
    return nodesPerRack;
  }
}
