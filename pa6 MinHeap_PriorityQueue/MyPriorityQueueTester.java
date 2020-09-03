/**
 * JUnit Test class for MyPriorityQueue
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Testing all the methods in MyPriorityQueue
 */

 public class MyPriorityQueueTester {

     private MyPriorityQueue<Integer> empty;
     private MyPriorityQueue<Integer> one;
     private MyPriorityQueue<Integer> oneToTen;


     @Before
     public void setUp()
     {
         empty = new MyPriorityQueue<>();

         ArrayList<Integer> uno = new ArrayList<>(1);
         uno.add(1);
         one = new MyPriorityQueue<>(uno);

         Integer[] ten = {1,2,3,4,5,6,7,8,9,10};
         oneToTen = new MyPriorityQueue<>(Arrays.asList(ten));
     }

     /**
      * Test both constructors
      */
     @Test
     public void testConstructors()
     {

         try{
             Integer[] timeToFail = {1,2,3,null,4,5};
             empty = new MyPriorityQueue<>(Arrays.asList(timeToFail));
         } catch (NullPointerException e) {
             //pass
         }

         try{
             MyPriorityQueue<Integer> uhoh = new MyPriorityQueue<>(null);
         } catch (NullPointerException e) {
             //pass
         }

         Integer[] outOfOrder = {1,2,4,0,6};

         one = new MyPriorityQueue<>(Arrays.asList(outOfOrder));
         assertEquals(Integer.valueOf(0), one.pop());
         assertEquals(Integer.valueOf(1), one.pop());

     }

     /**
      * Test the push() method and its exceptions
      */
     @Test
     public void testPush()
     {
         one.push(2);
         one.push(4);
         assertEquals(new Integer(1), one.peek());
         one.push(0);
         assertEquals(new Integer(0), one.peek());

         try{
             one.push(null);
         } catch (NullPointerException e) {
             //pass
         }
     }

     /**
      * Test the peek() method when list is empty and not empty
      */
     @Test
     public void testPeek()
     {
         one.push(0);
         assertEquals(new Integer(0), one.peek());
         one.push(2);
         assertEquals(new Integer(0), one.peek());
         one.pop();
         assertEquals(new Integer(1), one.peek());
         assertNull(empty.peek());
     }

     /**
      * Test the pop() method when list is empty and not empty
      */
     @Test
     public void testPop()
     {
         assertEquals(new Integer(1), one.pop());
         assertEquals(0, one.getLength());
         assertNull(one.pop());
         assertNull(empty.pop());
         assertEquals(new Integer(1), oneToTen.pop());
         assertEquals(new Integer(2), oneToTen.pop());
         oneToTen.push(100);
         assertEquals(new Integer(3), oneToTen.pop());

     }

     /**
      * Test the getLength() method
      */
     @Test
     public void testGetLength()
     {
         assertEquals(0, empty.getLength());
         assertEquals(1, one.getLength());
         assertEquals(10, oneToTen.getLength());

         oneToTen.push(0);
         oneToTen.push(4);
         assertEquals(12, oneToTen.getLength());
         empty.push(-1);
         assertEquals(1, empty.getLength());

     }

     /**
      * Test the clear() method
      */
     @Test
     public void testClear()
     {
         assertEquals(0, empty.getLength());
         assertEquals(1, one.getLength());
         one.clear();
         assertEquals(0, one.getLength());
         oneToTen.clear();
         assertEquals(0, oneToTen.getLength());
     }
 }
