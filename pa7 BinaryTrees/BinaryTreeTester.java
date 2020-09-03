/**
 * JUnit tester class for BinaryTree
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class BinaryTreeTester {

    private BinaryTree<Integer> empty;
    private BinaryTree<Integer> one;
    private BinaryTree<Integer> treeOfThree;

    /**
     * Setup, but also tests constructors
     */
    @Before
    public void setUp()
    {
        empty = new BinaryTree<Integer>();
        one = new BinaryTree<Integer>(1);

        Integer[] ott = {1,2,3};
        List<Integer> unoDosTres = new ArrayList<>(Arrays.asList(ott));
        treeOfThree = new BinaryTree<Integer>(unoDosTres);
    }

    /**
     * Tests the add method
     */
    @Test
    public void testAdd()
    {
        empty.add(1);
        assertTrue(empty.containsBFS(1));
        one.add(1);
        assertTrue(empty.containsBFS(1));

        try{
            treeOfThree.add(null);
        } catch (NullPointerException e) {
            // pass
        }
    }

    /**
     * Test true and false returns for the remove method
     */
    @Test
    public void testRemove()
    {
        assertFalse(one.remove(2));
        assertFalse(empty.remove(0));
        assertFalse(treeOfThree.remove(4));

        assertTrue(one.remove(1));
        assertEquals(0, one.getSize());
        assertFalse(one.containsBFS(1));

        treeOfThree.add(3);
        assertEquals(4, treeOfThree.getSize());
        assertTrue(treeOfThree.remove(3));
        assertEquals(3, treeOfThree.getSize());
        assertTrue(treeOfThree.containsBFS(3));

        treeOfThree.add(4);
        treeOfThree.add(5);
        treeOfThree.add(6);
        treeOfThree.add(7);
        treeOfThree.add(8);

        assertTrue(treeOfThree.remove(2));
        assertTrue(treeOfThree.remove(5));
        assertTrue(treeOfThree.remove(6));
        assertTrue(treeOfThree.remove(7));
        assertTrue(treeOfThree.remove(8));

        assertEquals(0, one.getSize());
        one.add(1);
        one.add(2);
        one.add(3);
        one.add(4);

        one.remove(3);
        one.remove(2);
        one.remove(1);
        one.remove(4);
        assertEquals(0, one.getSize());

        try{
            treeOfThree.remove(null);
        } catch (NullPointerException e) {
            // pass
        }
    }

    /**
     * Tests the search method for elements that exist and dont exist
     */
    @Test
    public void testContainsBFS()
    {
        assertTrue(one.containsBFS(1));
        assertTrue(treeOfThree.containsBFS(1));
        assertTrue(treeOfThree.containsBFS(2));
        assertTrue(treeOfThree.containsBFS(3));

        assertFalse(empty.containsBFS(1));
        assertFalse(one.containsBFS(-1));

        assertFalse(treeOfThree.containsBFS(123));
        treeOfThree.add(123);
        assertTrue(treeOfThree.containsBFS(123));

        try{
            treeOfThree.containsBFS(null);
        } catch (NullPointerException e) {
            // pass
        }
    }

    /**
     * Tests the getSize() method
     */
    @Test
    public void testGetSize()
    {
        assertEquals(1, one.getSize());
        assertEquals(3, treeOfThree.getSize());
        assertEquals(0, empty.getSize());

        empty.add(100000);
        assertEquals(1, empty.getSize());
        empty.add(100000);
        assertEquals(2, empty.getSize());

    }

    /**
     * Tests the getHeight method
     */
    @Test
    public void testGetHeight()
    {
        assertEquals(1, treeOfThree.getHeight());
        assertEquals(0, one.getHeight());
        assertEquals(0, empty.getHeight());

        treeOfThree.add(4);
        assertEquals(2, treeOfThree.getHeight());
        treeOfThree.add(5);
        assertEquals(2, treeOfThree.getHeight());
        treeOfThree.add(6);
        treeOfThree.add(7);
        treeOfThree.add(8);
        assertEquals(3, treeOfThree.getHeight());

        one.add(2);
        assertEquals(1, one.getHeight());
    }

    /**
     * Tests the minValue method
     */
    @Test
    public void testMinValue()
    {
        assertEquals(Integer.valueOf(1), treeOfThree.minValue());
        assertNull(empty.minValue());
        treeOfThree.add(-1);
        assertEquals(Integer.valueOf(-1), treeOfThree.minValue());
    }


}
