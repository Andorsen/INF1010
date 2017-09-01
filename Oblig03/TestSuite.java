class TestSuite
{
  /**
  * toDo
  * -Tabell<T>
  *   -static
  *   -dynamic
  * -Liste<T>
  *   -Stack
  *   -Queue
  *   -SortedLinkedList
  */
  public static void main(String[] args)
  {
    testTabell();
    testDynamiskTabell();

  }

  static void testDynamiskTabell()
  {
    DynamiskTabell<String> dt = new DynamiskTabell<>();
    System.out.println(dt.hentFraPlass(0));
    System.out.println(dt.hentFraPlass(1));
  }

  static void testTabell()
  {
    StatiskTabell<String> st = new StatiskTabell<>();
    st.settInn("Plass null");
    assertEquals("Plass null", st.hentFraPlass(0));
    st.settInn("Plass en");
    assertEquals("Plass en", st.hentFraPlass(1));
  }

  public static void assertEquals(String a, String b)
  {
    System.out.println(a.equals(b) ? "Pass": "Fail");
  }
}
