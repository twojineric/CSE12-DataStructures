/**
 * JUnit tester class for MyMinHeap
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
 * Tester class for MyMinHeap - tests constructors and methods
 */
public class MyMinHeapTester {

    private MyMinHeap<Integer> integerHeap;
    private MyMinHeap<Integer> sanityIntegerHeap;
    private MyMinHeap<Character> characterHeap;
    private MyMinHeap<Double> doubleHeap;

    ArrayList<Integer> listIntsRegularHeap;
    ArrayList<Integer> listIntsSmallHeap;
    ArrayList<Integer> listIntsDuplicatesHeap;

    private static final Integer[] arrIntsRegular = {5, 8, 10, 9, 11};
    private static final Integer[] arrIntsSmall = {3, 4, 5};
    private static final Integer[] arrIntsDup = {5, 8, 9, 8, 10, 11, 11};

    /**
     * This method runs before every method tagged with @Test.
     * Feel free to add your own MyMinHeap variables and their
     * initializations here.
     */
    @Before
    public void setUp() {
        integerHeap = new MyMinHeap<>();
        sanityIntegerHeap = new MyMinHeap<>();
        characterHeap = new MyMinHeap<>();
        doubleHeap = new MyMinHeap<>();
        listIntsRegularHeap = new ArrayList<>(Arrays.asList(arrIntsRegular));
        listIntsSmallHeap = new ArrayList<>(Arrays.asList(arrIntsSmall));
        listIntsDuplicatesHeap = new ArrayList<>(Arrays.asList(arrIntsDup));
    }

    @Test
    public void testConstructors()
    {
        try{
            Integer[] timeToFail = {1,2,3,null,4,5};
            MyMinHeap<Integer> bad = new MyMinHeap<>(Arrays.asList(timeToFail));
        } catch (NullPointerException e) {
            //pass
        }

        try{
            MyMinHeap<Integer> alsoBad = new MyMinHeap<>(null);
        } catch (NullPointerException e) {
            //pass
        }

    }

    /**
     * Runs the sanity test for swap() given in the write-up.
     */
    @Test
    public void testSwapSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{3,1,2});
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        sanityIntegerHeap.swap(0,1);
        Integer[] expected = {1,3,2};
        assertEquals(Arrays.asList(expected), sanityIntegerHeap.list);
    }

    /**
     * Runs the sanity test for getParentIdx() given in the write-up.
     */
    @Test
    public void testGetParentIdxSanity() {
        assertEquals(0, characterHeap.getParentIdx(1));
        assertEquals(new ArrayList<>(), characterHeap.list);
    }

    /**
     * Runs the sanity test for getLeftChildIdx() given in the write-up.
     */
    @Test
    public void testGetLeftChildIdxSanity() {
        assertEquals(1, characterHeap.getLeftChildIdx(0));
        assertEquals(new ArrayList<>(), characterHeap.list);
    }

    /**
     * Runs the sanity test for getRightChildIdx() given in the write-up.
     */
    @Test
    public void testGetRightChildIdxSanity() {
        assertEquals(2, characterHeap.getRightChildIdx(0));
        assertEquals(new ArrayList<>(), characterHeap.list);
    }

    /**
     * Runs the sanity test for getMinChildIdx() given in the write-up.
     */
    @Test
    public void testGetMinChildIdxSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{5,3,4});
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        assertEquals(1, sanityIntegerHeap.getMinChildIdx(0));
        assertEquals(new ArrayList<>(startingList), sanityIntegerHeap.list);

        assertEquals(-1, sanityIntegerHeap.getMinChildIdx(3));
        sanityIntegerHeap.insert(20);
        assertEquals(3, sanityIntegerHeap.getMinChildIdx(1));
    }

    /**
     * Runs the sanity test for percolateUp() given in the write-up.
     */
    @Test
    public void testPercolateUpSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{1,4,4,2,2});
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        sanityIntegerHeap.percolateUp(3);
        Integer[] expected = {1,2,4,4,2};
        assertEquals(Arrays.asList(expected), sanityIntegerHeap.list);

        List<Integer> test1 = Arrays.asList(new Integer[]{9,11,12,1,13});
        sanityIntegerHeap.list = new ArrayList<>(test1);
        sanityIntegerHeap.percolateUp(3);
        Integer[] answer = {1,9,12,11,13};
        assertEquals(Arrays.asList(answer), sanityIntegerHeap.list);

        List<Integer> test2 = Arrays.asList(new Integer[]{1,1,1,1,0,1,1});
        sanityIntegerHeap.list = new ArrayList<>(test2);
        sanityIntegerHeap.percolateUp(4);
        Integer[] solution = {0,1,1,1,1,1,1};
        assertEquals(Arrays.asList(solution), sanityIntegerHeap.list);

        List<Integer> test3 = Arrays.asList(new Integer[]{0,2,3,1,6});
        sanityIntegerHeap.list = new ArrayList<>(test3);
        sanityIntegerHeap.percolateUp(3);
        Integer[] correct = {0,1,3,2,6};
        assertEquals(Arrays.asList(correct), sanityIntegerHeap.list);
    }

    /**
     * Runs the sanity test for percolateDown() given in the write-up.
     */
    @Test
    public void testPercolateDownSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{8,4,3,5,2});
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        sanityIntegerHeap.percolateDown(0);
        Integer[] expected = {3,4,8,5,2};
        assertEquals(Arrays.asList(expected), sanityIntegerHeap.list);

        List<Integer> test1 = Arrays.asList(new Integer[]{100,1,2,3,4,5});
        sanityIntegerHeap.list = new ArrayList<>(test1);
        sanityIntegerHeap.percolateDown(0);
        Integer[] answer = {1,3,2,100,4,5};
        assertEquals(Arrays.asList(answer), sanityIntegerHeap.list);

    }

    /**
     * Runs a simple test for insert().
     */
    @Test
    public void testInsertSmall() {
        integerHeap.list = listIntsSmallHeap;
        integerHeap.insert(1);
        Integer[] expected = {1, 3, 5, 4};
        assertEquals(Arrays.asList(expected), integerHeap.list);
    }

    /**
     * Runs another simple test for insert().
     */
    @Test
    public void testInsertRegular() {
        integerHeap.list = listIntsRegularHeap;
        integerHeap.insert(1);
        Integer[] expected = {1, 8, 5, 9, 11, 10};
        assertEquals(Arrays.asList(expected), integerHeap.list);
    }

    /**
     * Runs a simple test for getMin().
     */
    @Test
    public void testGetMinSmall() {
        integerHeap.list = listIntsSmallHeap;
        assertEquals(Integer.valueOf(3), integerHeap.getMin());
    }

    /**
     * Runs another simple test for getMin().
     */
    @Test
    public void testGetMinRegular() {
        integerHeap.list = listIntsRegularHeap;
        assertEquals(Integer.valueOf(5), integerHeap.getMin());
    }

    /**
     * Runs a simple test for remove().
     */
    @Test
    public void testRemoveSmall() {
        integerHeap.list = listIntsSmallHeap;
        assertEquals(Integer.valueOf(3), integerHeap.remove());
        Integer[] expected = {4, 5};
        assertEquals(Arrays.asList(expected), integerHeap.list);
    }

    /**
     * Runs another simple test for remove().
     */
    @Test
    public void testRemoveRegular() {
        integerHeap.list = listIntsRegularHeap;
        assertEquals(Integer.valueOf(5), integerHeap.remove());
        Integer[] expected = {8, 9, 10, 11};
        assertEquals(Arrays.asList(expected), integerHeap.list);
    }

    /**
     * Runs a simple test for size().
     */
    @Test
    public void testSizeSmall() {
        integerHeap.list = listIntsSmallHeap;
        assertEquals(3, integerHeap.size());
    }

    /**
     * Runs another simple test for size().
     */
    @Test
    public void testSizeRegular() {
        integerHeap.list = listIntsRegularHeap;
        assertEquals(5, integerHeap.size());
    }

    /**
     * Runs a simple test for clear().
     */
    @Test
    public void testClear() {
        integerHeap.list = listIntsSmallHeap;
        integerHeap.clear();
        assertNotNull(integerHeap.list);
        assertEquals(0, integerHeap.size());
    }
}
