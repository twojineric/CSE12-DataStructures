/**
 * Tester file to test the class MyQueue
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 *
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTester{

    private MyQueue<Integer> emptyQ;
    private MyQueue<Integer> oneToTen;

    @Before
    public void setUp()
    {
        emptyQ = new MyQueue<>(5);
        oneToTen = new MyQueue<>(10);

        Integer[] ten = {1,2,3,4,5,6,7,8,9,10};

        oneToTen.theQueue.data = ten;
        oneToTen.theQueue.size = 10;
        oneToTen.theQueue.rear = 9;
    }

    /**
     * Tests enqueue()
     */
    @Test
    public void testEnqueue()
    {
        emptyQ.enqueue(5);
        emptyQ.enqueue(4);
        emptyQ.enqueue(3);
        emptyQ.enqueue(2);
        emptyQ.enqueue(1);

        emptyQ.enqueue(100); 

        assertEquals(new Integer(5), emptyQ.dequeue());
        assertEquals(new Integer(4), emptyQ.dequeue());
        assertEquals(new Integer(3), emptyQ.dequeue());
        assertEquals(new Integer(2), emptyQ.dequeue());
        assertEquals(new Integer(1), emptyQ.dequeue());

        try{
            emptyQ.enqueue(null);
            fail("Expected NullPointerException");
        } catch(NullPointerException e){
            //test passes
        }
    }

    /**
     * Tests dequeue(), makes sure ADT is followed
     */
    @Test
    public void testDequeue()
    {
        assertNull(emptyQ.dequeue());
        assertEquals(new Integer(1), oneToTen.dequeue());
        assertEquals(new Integer(2), oneToTen.dequeue());
    }

    /**
     * Tests empty()
     */
    @Test
    public void testEmpty()
    {
        assertTrue(emptyQ.empty());
        assertTrue(!oneToTen.empty());
        emptyQ.enqueue(-999);
        assertTrue(!emptyQ.empty());
    }

    /**
     * Tests peek() with null and non-null return values
     */
    @Test
    public void testPeek()
    {
        assertNull(emptyQ.peek());
        assertEquals(new Integer(1), oneToTen.peek());
        assertEquals(new Integer(1), oneToTen.dequeue());
        assertEquals(new Integer(2), oneToTen.peek());
    }

}
