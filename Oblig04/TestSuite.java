class TestSuite
{
  public static void main(String[] args)
  {
    Pasient p1 = new Pasient("1Ola", 1231, "Hjemmegata1", 0101);
    Pasient p2 = new Pasient("2Ola", 1232, "Hjemmegata2", 0102);
    Pasient p3 = new Pasient("3Ola", 1233, "Hjemmegata3", 0103);

    System.out.println(p1.toString());
    System.out.println(p2.toString());
    System.out.println(p3.toString());
  }
}
