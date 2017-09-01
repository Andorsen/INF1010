class WhiteTile extends Tile
{
  public WhiteTile(int x, int y, Tile[][] pMap)
  {
    super(x, y, pMap);
    cost = Integer.MAX_VALUE;
  }
}
