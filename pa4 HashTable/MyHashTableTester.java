
/**
* NAME: Eric Jin
* ID: cs12sp20aum
* PID: A15816483
* EMAIL: erjin@ucsd.edu
*
*/
import org.junit.*;
import static org.junit.Assert.*;

public class MyHashTableTester {

    private MyHashTable hashTable1;

    @Before
    public void setUp()
    {
        hashTable1 = new MyHashTable(13);
    }

    @Test
    public void testConstructors()
    {
        try {
            MyHashTable timeToFail = new MyHashTable(-2);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // pass
        }

        try {
            MyHashTable timeToFail2 = new MyHashTable(3, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // pass
        }
    }

    /**
    * Test inserting a valid string and a string that already exists
    *
    */
    @Test
    public void testInsert()
    {
        assertEquals("Checking insert", true, hashTable1.insert("abc"));
        assertEquals("Checking contains after insert", true,
        hashTable1.contains("abc"));
        assertTrue(!hashTable1.insert("abc"));
    }

    /**
    * Test inserting a null string
    *
    */
    @Test
    public void testInsertNullPointerException()
    {
        try {
            hashTable1.insert(null);
            fail("Expected an NullPointerException to be thrown");
        } catch (NullPointerException e) {
            assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
        }
    }

    /**
    * Deletes a string, a null string and a string that doesnt exist
    *
    */
    @Test
    public void testDelete()
    {
        hashTable1.insert("abc");
        assertEquals("Checking delete", true, hashTable1.delete("abc"));
        assertEquals("Checking contains after delete", false,
        hashTable1.contains("abc"));

        assertTrue(!hashTable1.delete("cba"));

        try {
            hashTable1.insert(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // pass
        }
    }

    /**
    * Test the contains method with a null stirng, a string that is not in the
    * hash table and a regular string
    */
    @Test
    public void testContains()
    {
        try {
            hashTable1.contains(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // pass
        }

        hashTable1.insert("abc");
        assertTrue(!hashTable1.contains("qwerty"));
        assertTrue(hashTable1.contains("abc"));
    }

    /**
    * Test getSize
    *
    */
    @Test
    public void testGetSize()
    {
        hashTable1.insert("abc");
        hashTable1.insert("pqr");
        hashTable1.insert("xyz");
        assertEquals("Checking getSize", Integer.valueOf(3),
        Integer.valueOf(hashTable1.getSize()));
    }

    /**
    * Test printTable and checks to see if the size of the hashTable increased
    * to the correct value.
    *
    */
    @Test
    public void testPrintTable()
    {
        hashTable1.printTable();
        System.out.println("elems = " + hashTable1.nelems);
        System.out.println("Length = " + hashTable1.array.length);
        hashTable1.insert("abc");
        hashTable1.insert("pqr");
        hashTable1.insert("xyz");
        hashTable1.insert("def");
        hashTable1.insert("ghi");
        hashTable1.insert("jkl");
        hashTable1.insert("mno");
        hashTable1.insert("www");
        hashTable1.insert("lol");
        hashTable1.insert("cya");
        hashTable1.printTable();
        System.out.println("elems = " + hashTable1.nelems);
        System.out.println("Length = " + hashTable1.array.length);
        hashTable1.insert("bye");
        hashTable1.insert("yoo");
        hashTable1.insert("nbc");
        hashTable1.insert("cbs");
        hashTable1.insert("fox");
        hashTable1.insert("cnn");
        hashTable1.printTable();
        System.out.println("elems = " + hashTable1.nelems);
        System.out.println("Length = " + hashTable1.array.length);

    }

}
