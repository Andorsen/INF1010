class Tester
{
  public static void main(String[] args)
  {
    int nodesPerRack = 12;
    Cluster cluster = new Cluster(nodesPerRack);

    System.out.println("\n\nCluster - Nodes per Rack: "+
    (cluster.getNodesPerRack()==nodesPerRack ? "Pass" : ("Fail!\nExpected: 12\nFound: "+cluster.getNodesPerRack())));

    cluster.addNodes(650, 64, 2, 8, 2.6);
    double cfExpected = (650*2*8*(2.6*1000*1000000)*8);
    double cfFound = (cluster.getClusterFlops());
    System.out.println("Cluster - FlopsCount: "+
    (cfExpected==cfFound ? "Pass": ("FAIL!\nExpected: "+cfExpected+"\nFound: "+cfFound)));

    cluster.addNodes(16, 1024, 2, 8, 2.3);
    cfExpected += (16*2*8*(2.3*1000*1000000)*8);
    cfFound = (cluster.getClusterFlops());
    System.out.println("Cluster - FlopsCount: "+
    (cfExpected==cfFound ? "Pass": ("FAIL!\nExpected: "+cfExpected+"\nFound: "+cfFound)));

    System.out.println("Cluster - NodeCount: "+
    (cluster.nodeCount(32) == 666 ? "Pass" : ("Fail!\nExpected: 666\nFound: "+cluster.nodeCount(32))));
    System.out.println("Cluster - NodeCount: "+
    (cluster.nodeCount(64) == 666 ? "Pass" : ("Fail!\nExpected: 666\nFound: "+cluster.nodeCount(64))));
    System.out.println("Cluster - NodeCount: "+
    (cluster.nodeCount(128) == 16 ? "Pass" : ("Fail!\nExpected: 16\nFound: "+cluster.nodeCount(128))));

    System.out.println("Cluster - RackCount: "+
    (cluster.getRackCount()==56 ? "Pass" : ("Fail!\nExpected: 56\nFound: "+cluster.getRackCount())));
  }
}
