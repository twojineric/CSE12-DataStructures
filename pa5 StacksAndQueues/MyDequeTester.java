/**
 * Tester file to test the class MyDeque
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 *
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyDequeTester{

    private MyDeque<Integer> capZero;
    private MyDeque<Integer> empty;
    private MyDeque<Integer> oneElem;
    private MyDeque<Integer> capOne;
    private MyDeque<Integer> several;
    private MyDeque<Integer> full;
    private MyDeque<Integer> frontBehindRear;
    private final int SIZE = 7;
    private final int SEVERAL = 3;
    private final int DEFAULT_CAP = 10;
    private final int EXPAND_FACTOR = 2;

    @Before
    public void setUp(){
        capZero = new MyDeque<>(0);
        empty = new MyDeque<>(SIZE);
        oneElem = new MyDeque<>(SIZE);
        capOne = new MyDeque<>(1);
        several = new MyDeque<>(SIZE);
        full = new MyDeque<>(SIZE);
        frontBehindRear = new MyDeque<>(SIZE);

        oneElem.data[0] = 0;
        oneElem.size = 1;
        Integer[] severalArr = {0, 1, 2, null, null, null, null};
        several.data = severalArr;
        several.size = SEVERAL;
        several.rear = SEVERAL - 1;

        Integer[] fullArr = {0, 1, 2, 3, 4, 5, 6};
        full.data = fullArr;
        full.size = SIZE;
        full.rear = SIZE - 1;

        Integer[] frontBehindRearArr = {3, 4, null, null, 0, 1, 2};
        frontBehindRear.data = frontBehindRearArr;
        frontBehindRear.front = 4;
        frontBehindRear.rear = 1;
        frontBehindRear.size = 5;
    }

    /**
     * Tests constructor Exception
     *
     */
    @Test
    public void testConstructorException(){
        try{
            MyDeque<Integer> illegal = new MyDeque<>(-1);
            fail("Did not catch constructor exception");
        } catch(IllegalArgumentException exc){

        }
    }

    /**
     * Tests size(), makes sure size doesnt change after call to size()
     *
     */
    @Test
    public void testSize()
    {
        assertEquals(7, full.size());
        full.removeFirst();
        assertEquals(6, full.size());
        capZero.addFirst(0);
        assertEquals(1, capZero.size());
    }

    /**
     * Tests arraysize is correctly changed and the front element is
     * assigned to index 0.
     */
    @Test
    public void testExpandCapacity()
    {
        Integer[] expected =
            {-1 ,0, 1, 2, 3, 4, 5, 100, null, null, null, null, null, null};

        frontBehindRear.addLast(5);
        frontBehindRear.addFirst(-1);
        frontBehindRear.addLast(100);
        assertEquals(expected, frontBehindRear.data);

        Integer[] zeroToHero =
            {0, null, null, null, null, null, null, null, null, null};

        capZero.addLast(0);
        assertEquals(zeroToHero, capZero.data);
        assertEquals(new Integer(0), capZero.peekFirst());
        assertEquals(new Integer(0), capZero.peekLast());

        capOne.addFirst(100);
        capOne.addFirst(101);
        assertEquals(2, capOne.size());
        assertEquals(new Integer(101), capOne.peekFirst());
        assertEquals(new Integer(100), capOne.peekLast());
    }

    /**
     * Tests that peekFirst() and peekLast() do not change
     * the first and last element.
     */
    @Test
    public void testPeekFirstAndLast()
    {
        assertEquals(new Integer(0), full.peekFirst());
        assertEquals(new Integer(0), frontBehindRear.peekFirst());
        assertEquals(new Integer(4), frontBehindRear.peekLast());
        frontBehindRear.removeLast();
        assertEquals(new Integer(3), frontBehindRear.peekLast());
        frontBehindRear.removeLast();
        assertEquals(new Integer(2), frontBehindRear.peekLast());

        assertNull(capZero.peekLast());
        assertNull(capZero.peekFirst());

    }

    /**
     * Tests that the methods correctly remove and reassign front and rear.
     *
     */
    @Test
    public void testRemoveFirstAndLast()
    {
        assertEquals(new Integer(4), frontBehindRear.removeLast());
        frontBehindRear.removeLast();
        assertEquals(new Integer(2), frontBehindRear.removeLast());
        frontBehindRear.removeFirst();
        assertEquals(new Integer(1), frontBehindRear.removeFirst());
        assertNull(frontBehindRear.removeFirst());
        assertNull(frontBehindRear.removeLast());

        assertEquals(new Integer(0), oneElem.removeLast());
    }

    /**
     * Test various edge cases for addFirst() and addLast()
     *
     */
    @Test
    public void testAddFirstAndLast()
    {
        assertNull(empty.peekFirst());
        assertNull(empty.peekLast());
        empty.addFirst(1);
        assertEquals(new Integer(1), empty.peekFirst());
        assertEquals(new Integer(1), empty.peekLast());
        empty.addFirst(0);
        assertEquals(new Integer(0), empty.peekFirst());
        assertEquals(new Integer(1), empty.peekLast());
        empty.addLast(2);
        assertEquals(new Integer(0), empty.peekFirst());
        assertEquals(new Integer(2), empty.peekLast());

        try{
            empty.addFirst(null);
            fail("Expected NullPointerException");
        } catch(NullPointerException e){
            // pass test
        }

        try{
            empty.addLast(null);
            fail("Expected NullPointerException");
        } catch(NullPointerException e){
            // pass test
        }
    }

    /**
     * Tests the constructors
     */
    @Test
    public void testConstructorNormal(){
        MyDeque<Integer> normal = new MyDeque<>(SIZE);
        assertEquals(new Integer[SIZE],  normal.data);
        assertEquals(0, normal.front);
        assertEquals(0, normal.rear);
        assertEquals(0, normal.size);
    }

    /**
     * Add many elements to an empty deque
     */
    @Test
    public void testAddFirstEmptyMultiple(){
        empty.addFirst(0);
        empty.addFirst(1);
        empty.addFirst(2);
        Integer[] expected = {0, null, null, null, null, 2, 1};
        assertEquals(expected,  empty.data);
        assertEquals(5, empty.front);
        assertEquals(0, empty.rear);
        assertEquals(3, empty.size);
    }

    /** When elements are in the middle of the array*/
      @Test
      public void testExpandCapacitySeveralEdge(){
          Integer[] severalEdge = {null, null, 0, 1, 2, null, null};
          several.data = severalEdge;
          several.front = 2;
          several.rear = 4;
          several.expandCapacity();
          Integer[] expanded = {0, 1, 2, null, null, null, null,
                            null, null, null, null, null, null, null};
          assertEquals(expanded,  several.data);
          assertEquals(0, several.front);
          assertEquals(SEVERAL - 1, several.rear);
          assertEquals(SEVERAL, several.size);

      }

      /**
       * Test remove last for edge cases
       */
    @Test
    public void testRemoveLastSeveralEdge(){
        Integer[] severalEdge = {1, null, null, null, null, -1, 0};
        several.data = severalEdge;
        several.front = 5;
        several.rear = 0;
        assertEquals(1, several.removeLast().intValue());
        Integer[] expected = {null, null, null, null, null, -1, 0};
        assertEquals(expected, several.data);
        assertEquals(5, several.front);
        assertEquals(6, several.rear);
        assertEquals(2, several.size);
    }

}
