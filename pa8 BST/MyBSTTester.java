
/**
 * JUnit tester class for BST
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class MyBSTTester {

    private MyBST<Integer, String> birch;
    private MyBST<Integer, String> oak;
    private MyBST<Integer, String> spruce;
    private MyBST<Integer, String> acacia;
    private MyBST<Integer, String> jungle;
    private MyBST<Integer, String> darkOak;


    @Before
    public void setUp()
    {

        /* BIRCH
                100
                /  \
              50  1000
              /
             1
              \
               2
                \
                 3
                  \
                   4
                    ...
        */
        birch = new MyBST<Integer, String>();
        birch.insert(100, "100");
        birch.insert(1000, "1000");
        birch.insert(50, "50");
        for(int i = 1; i <= 10; i++)
        {
            birch.insert(i, "" + i);
        }

        /* OAK
                    10
                   /  \
                  5    15
                 / \     \
                2   8     20
               / \        /
              1   3      16
                   \
                    4
        */
        oak = new MyBST<Integer, String>();
        oak.insert(10, "10");
        oak.insert(5, "5");
        oak.insert(8, "8");
        oak.insert(15, "15");
        oak.insert(20, "20");
        oak.insert(16, "16");
        oak.insert(2, "2");
        oak.insert(1, "1");
        oak.insert(3, "3");
        oak.insert(9, "9");

        /* SPRUCE
                     0
                   /   \
                 -2     2
                 / \   / \
               -4  -1 1   4
        */
        spruce = new MyBST<Integer, String>();
        spruce.insert(0, "0");
        spruce.insert(-2, "-2");
        spruce.insert(-4, "-4");
        spruce.insert(-1, "-1");
        spruce.insert(2, "2");
        spruce.insert(1, "1");
        spruce.insert(4, "4");

        acacia = new MyBST<Integer, String>();
        acacia.insert(1, "1");
        jungle = new MyBST<Integer, String>();
        jungle.insert(5, "5");
        darkOak = new MyBST<Integer, String>();

    }


    @Test
    public void testRemove()
    {
        assertEquals("1", oak.remove(1));       //leaf
        assertEquals("100", birch.remove(100));
        assertEquals("1000", birch.remove(1000)); //remove the largest child
        assertEquals("3", oak.remove(3));       //one child
        assertEquals("20", oak.remove(20)); //remove largest child
        assertEquals("1", birch.remove(1));
        assertEquals("10", birch.remove(10));
        assertEquals("5", oak.remove(5));       //two children
        assertEquals("10", oak.remove(10));     //root
        oak.insert(0, null);
        assertNull(oak.remove(123)); //remove item that doesnt exist
        assertNull(oak.remove(0)); //remove item with value null

        assertNull(darkOak.remove(100)); //remove from an empty tree
        assertEquals("5", jungle.remove(5)); //remove root from tree w/ size = 1

    }

    @Test
    public void testInsert()
    {
        assertNull(jungle.insert(2, "2"));
        assertNull(jungle.insert(10, "10"));
        assertNull(oak.insert(25, "25"));
        assertNull(oak.insert(100, null));
        assertNull(oak.insert(100, null));

        assertEquals("1", acacia.insert(1, "new root"));
        assertEquals("1", oak.insert(1, "new left leaf"));
        assertEquals("4", oak.insert(4, "new right leaf"));
        assertEquals("5", oak.insert(5, "new node with two children"));

        assertNull(darkOak.insert(-1, "first node"));

        try{
            jungle.insert(null, "whoops");
        } catch (NullPointerException e) {
            //pass test
        }

    }

    @Test
    public void testSearch()
    {
        assertEquals("1", spruce.search(1));
        assertEquals("-4", spruce.search(-4));
        assertEquals("0", spruce.search(0));
        assertEquals("8", oak.search(8));
        assertEquals("2", oak.search(2));
        assertEquals("5", jungle.search(5));

        assertNull(oak.search(6));
        assertNull(birch.search(11));
        assertNull(darkOak.search(31415));

        oak.insert(0, null);
        assertNull(oak.search(0));
    }

    @Test
    public void testSize()
    {
        assertEquals(10, oak.size());
        assertEquals(1, jungle.size());
        assertEquals(0, darkOak.size());
        assertEquals(13, birch.size());

        oak.insert(30, "30");
        darkOak.insert(100000, "100000");
        jungle.insert(10, "10");
        assertEquals(11, oak.size());
        assertEquals(1, darkOak.size());
        assertEquals(2, jungle.size());

        acacia.remove(1);
        assertEquals(0, acacia.size());
        acacia.remove(1);
        assertEquals(0, acacia.size());

        oak.remove(16);
        assertEquals(10, oak.size());
        oak.remove(5);
        assertEquals(9, oak.size());
        oak.remove(10);
        assertEquals(8, oak.size());
        oak.remove(100000);
        assertEquals(8, oak.size());

    }

    @Test
    public void testInorder()
    {
        assertEquals(0, darkOak.inorder().size());

        ArrayList<MyBST.MyBSTNode<Integer, String>> ordered1 = spruce.inorder();
        Integer[] expected1 = {-4,-2,-1,0,1,2,4};

        for(int i = 0; i < expected1.length; i++)
        {
            assertEquals(expected1[i], ordered1.get(i).getKey());
        }

        ArrayList<MyBST.MyBSTNode<Integer, String>> ordered2 = birch.inorder();
        Integer[] expected2 = {1,2,3,4,5,6,7,8,9,10,50,100,1000};

        for(int i = 0; i < expected2.length; i++)
        {
            assertEquals(expected2[i], ordered2.get(i).getKey());
        }

        ArrayList<MyBST.MyBSTNode<Integer, String>> ordered3 = oak.inorder();
        Integer[] expected3 = {1,2,3,4,5,8,10,15,16,20};

        for(int i = 0; i < expected3.length; i++)
        {
            assertEquals(expected3[i], ordered3.get(i).getKey());
        }

    }

    @Test
    public void testCSEFINAL()
    {
        oak.covid19(oak.root);
    }

}
