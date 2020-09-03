
import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Title: class MyLinkedListTester
 *
 * @author Eric Jin
 * @version 3.0 05-April-2015 Student ID: A15816483 CSE12 Account: cs12sp20aum
 * Date: 4/20/2020
 *
 * Description: Use JUnit to test the various methods of MyLinkedListTester<E>
 *
 */
public class MyLinkedListTester {

    private MyLinkedList<Integer> empty;
    private MyLinkedList<Integer> one;
    private MyLinkedList<Integer> several;
    private MyLinkedList<String> slist;
    static final int DIM = 5;
    static final int FIBMAX = 30;
    private ListIterator<Integer> iterTest;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and a
     * list with several entries (0,1,2)
     */
    @Before
    public void setUp()
    {
        empty = new MyLinkedList<Integer>();
        one = new MyLinkedList<Integer>();
        one.add(0, new Integer(0));
        several = new MyLinkedList<Integer>();
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
        {
            several.add(0, new Integer(i));
        }

        // List: "First","Last"
        slist = new MyLinkedList<String>();
        slist.add(0, "First");
        slist.add(1, "Last");
    }


    /**
     * Test if first node of the lists are correct
     */
    @Test
    public void testGetFirst()
    {
        assertEquals("Check 0", new Integer(0), one.get(0));
        assertEquals("Check 0", new Integer(1), several.get(0));
    }

    /**
     * Test if size of lists are correct
     */
    @Test
    public void testListSize()
    {
        assertEquals("Check Empty Size", 0, empty.size());
        assertEquals("Check One Size", 1, one.size());
        assertEquals("Check Several Size", DIM, several.size());
    }

    /**
     * Test setting a specific entry
     */
    @Test
    public void testSet()
    {
        slist.set(1, "Final");
        assertEquals("Setting specific value", "Final", slist.get(1));

        assertEquals("Test correct return val",
          new Integer(DIM), several.set(DIM - 1, DIM + 1));
        assertEquals("test correct return val",
          new Integer(1), several.set(0, 31415));
        assertEquals("Correct set", new Integer(DIM + 1), several.get(DIM - 1));

        try{
            several.set(2, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // pass
        }

        try{
          several.set(-100, 1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }

        try{
          several.set(123, 1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }

    }

    /**
     * Test isEmpty
     */
    @Test
    public void testEmpty()
    {
        assertTrue("empty is empty", empty.isEmpty());
        assertTrue("one is not empty", !one.isEmpty());
        assertTrue("several is not empty", !several.isEmpty());
    }

    /**
     * Test out of bounds exception on get
     */
    @Test
    public void testGetException()
    {
        try{
            empty.get(0);
            fail("Should have generated an exception");
        } catch (IndexOutOfBoundsException e) {
            //  normal
        }

        try{
            one.get(-1);
            fail("Should have generated an exception");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }

        try{
            one.get(2);
            fail("Should have generated an exception");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    /**
     * Test add methods
     */
    @Test
    public void testAdd()
    {
       slist.add(1, "Second");
       slist.add(2, "Third");
       slist.add(4, "Also last");
       slist.add(5, "this one is last ");
       slist.add(6, "sike u thought");

     //[first, second, third, last, also last, this one is last, sike u thought]

      assertEquals("Also last", slist.get(4));
      assertEquals("sike u thought", slist.get(6));
      assertEquals("First", slist.get(0));
      assertEquals("Third", slist.get(2));

      slist.clear();
      slist.add(0, "no longer empty");
      assertEquals("Add string to empty list", "no longer empty", slist.get(0));

      try{
        slist.add(1, null);
          fail("Expected NullPointerException");
      } catch (NullPointerException e) {
          // pass
      }

      try{
        slist.add(-1, "the new zero");
          fail("Expected IndexOutOfBoundsException");
      } catch (IndexOutOfBoundsException e) {
          // pass
      }

      try{
        slist.add(1000, "infinity");
          fail("Expected IndexOutOfBoundsException");
      } catch (IndexOutOfBoundsException e) {
          // pass
      }

    }

    /**
     * Test isEmpty() and clear() methods
     */
    @Test
    public void testIsEmptyAndtestClear()
    {
      assertTrue(empty.isEmpty());
      empty.clear();
      assertTrue(empty.isEmpty());
      assertTrue(!one.isEmpty());
      one.clear();
      assertTrue(one.isEmpty());
    }

    @Test
    public void testRemove()
    {
      assertEquals("test return type", new Integer(0), one.remove(0));
      assertTrue(one.isEmpty());

      several.remove(2); //[1,2,4,5]
      assertEquals(new Integer(1), several.get(0));
      assertEquals(new Integer(2), several.get(1));
      assertEquals(new Integer(4), several.get(2));
      assertEquals(new Integer(5), several.get(3));
      several.remove(3);
      assertEquals(new Integer(4), several.get(2));

      try{
        several.remove(-1);
          fail("Expected IndexOutOfBoundsException");
      } catch (IndexOutOfBoundsException e) {
          // pass
      }

      try{
        several.remove(321);
          fail("Expected IndexOutOfBoundsException");
      } catch (IndexOutOfBoundsException e) {
          // pass
      }
    }

    @Test
    public void testSize()
    {
      assertEquals(0, empty.size());
      assertEquals(1, one.size());
      assertEquals(DIM, several.size());
      assertEquals(2, slist.size());
    }

    /**
     * Test iterator on empty list and several list
     */
    @Test
    public void testIterator()
    {
        int counter = 0;
        ListIterator<Integer> iter;
        for (iter = empty.listIterator(); iter.hasNext();)
        {
            fail("Iterating empty list and found element");
        }
        counter = 0;
        for (iter = several.listIterator(); iter.hasNext(); iter.next())
        {
            counter++;
        }
        assertEquals("Iterator several count", counter, DIM);
    }


    //////////////////////////////////////////
    //Begin testing on List Iterator methods//
    /////////////////////////////////////////
    /**
     * Test listiterator hasnext method while it goes through the empty and one
     * list
     */
    @Test
    public void testIteratorHasNext()
    {

        ListIterator<Integer> iter = empty.listIterator();
        ListIterator<Integer> iter1 = one.listIterator();

        assertTrue(!iter.hasNext());
        assertTrue(iter1.hasNext());
    }

    /**
     * Test listiterator hasprevious method while it goes through lists
     */
    @Test
    public void testIteratorHasPrevious()
    {

        ListIterator<Integer> iter = empty.listIterator();
        ListIterator<Integer> iter1 = one.listIterator();

        assertTrue(!iter.hasPrevious());
        assertTrue(!iter1.hasPrevious());
    }

    /**
     * Test listiterator next method
     */
    @Test
    public void testIteratorNext()
    {
        iterTest = several.listIterator();
        assertEquals(new Integer(1), iterTest.next());
        assertEquals(new Integer(2), iterTest.next());
        assertEquals(new Integer(3), iterTest.next());
        assertEquals(new Integer(4), iterTest.next());
        assertEquals(new Integer(5), iterTest.next());

        try{
          iterTest.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // pass
        }

        ListIterator<Integer> iterTest1 = empty.listIterator();

        try{
          iterTest1.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // pass
        }

    }

    /**
     * Test listiterator previous method
     */
    @Test
    public void testIteratorPrevious()
    {
        iterTest = several.listIterator();
        iterTest.next();
        iterTest.next();
        iterTest.next();

        assertEquals(new Integer(3), iterTest.previous());
        assertEquals(new Integer(2), iterTest.previous());
        assertEquals(new Integer(1), iterTest.previous());

        try{
          iterTest.previous();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // pass
        }

        ListIterator<Integer> iterTest1 = empty.listIterator();

        try{
          iterTest1.previous();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // pass
        }

    }

    /**
     * Test nextIndex method of list iterator
     */
    @Test
    public void testIteratorNextIndex()
    {
        iterTest = several.listIterator();

        //Test the nextIndex method at the start of and end
        //of the list as well as middle of the list
        assertEquals(0, iterTest.nextIndex());
        iterTest.next();
        iterTest.next();
        iterTest.next();
        assertEquals(3, iterTest.nextIndex());
        iterTest.next();
        iterTest.next();
        assertEquals(5, iterTest.nextIndex());

        ListIterator<Integer> iterTest1 = empty.listIterator();
        assertEquals(0, iterTest1.nextIndex());
    }

    /**
     * Test previousIndex method of list iterator
     */
    @Test
    public void testIteratorPreviousIndex()
    {
        iterTest = several.listIterator();
        iterTest.next();
        iterTest.next();
        assertEquals(1, iterTest.previousIndex());

        ListIterator<Integer> iterTest0 = one.listIterator();
        assertEquals(-1, iterTest0.previousIndex());
        ListIterator<Integer> iterTest1 = empty.listIterator();
        assertEquals(-1, iterTest1.previousIndex());

    }

    /**
     * Test the remove method of list iterator
     */
    @Test
    public void testIteratorRemove()
    {
        iterTest = several.listIterator();

        //Test whether removes method work after next method
        iterTest.next();
        iterTest.next();
        iterTest.remove();

        assertEquals(new Integer(1), iterTest.previous());

        //Test whether remove method work after previous method
        iterTest.next();
        iterTest.next();
        iterTest.previous();
        iterTest.remove();

        assertEquals(new Integer(4), iterTest.next());
        iterTest.remove();

        try{
          iterTest.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // pass
        }

        ListIterator<Integer> iterTest1 = empty.listIterator();

        try{
          iterTest1.remove();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // pass
        }

    }

}
