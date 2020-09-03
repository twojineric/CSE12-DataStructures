/**
 * Tester file to test the class MyStack
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 *
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTester{

    private MyStack<String> empty;
    private MyStack<String> ten;

    @Before
    public void setUp()
    {
        empty = new MyStack<String>(5);
        ten = new MyStack<String>(10);
    }

    /**
     * Tests empty()
     */
    @Test
    public void testEmpty()
    {
        assertTrue(empty.empty());
        empty.push("no longer empty");
        assertTrue(!empty.empty());
    }

    /**
     * Tests pop() with null and non-null return values
     */
    @Test
    public void testPop()
    {
        assertNull(empty.pop());
        empty.push("Bottom of the stack");
        empty.push("2nd from bottom");
        empty.push("3rd place");
        empty.push("2nd place");
        empty.push("first");

        assertEquals("first", empty.pop());
        assertEquals("2nd place", empty.pop());
        assertEquals("3rd place", empty.pop());
        empty.pop();
        assertEquals("Bottom of the stack", empty.pop());
        assertNull(empty.pop());
    }

    /**
     * Tests push() with null and non-null return values
     */
    @Test
    public void testPush()
    {
        empty.push("5");
        empty.push("4");
        empty.push("3");
        empty.push("2");
        empty.push("1");

        empty.push("321");

        assertEquals("321", empty.pop());
        assertEquals("1", empty.pop());
        assertEquals("2", empty.pop());
        assertEquals("3", empty.pop());
        assertEquals("4", empty.pop());
        assertEquals("5", empty.pop());

        try{
            empty.push(null);
            fail("Expected NullPointerException");
        } catch(NullPointerException e){
            //test passes
        }
    }

    /**
     * Tests peek() with null and non-null return values
     */
    @Test
    public void testPeek()
    {
        assertNull(empty.peek());
        empty.push("dont look at me");
        empty.push("look at this one instead");

        assertEquals("look at this one instead", empty.peek());
        assertEquals(2, empty.theStack.size);
    }
}
