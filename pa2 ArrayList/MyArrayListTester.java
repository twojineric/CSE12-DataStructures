/**
 * Class MyArrayListTester runs tests to see if the MyArrayList class' methods
 * all run properly.
 *
 *  NAME: Eric Jin
 *  ID: A15816483
 *  EMAIL: erjin@ucsd.edu
 *
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;


import org.junit.*;

public class MyArrayListTester {

  static final int DEFAULT_CAPACITY = 10;
  static final int MY_CAPACITY = 3;

  Object[] arr = new Object[10];
  Integer[] arrInts = {1,2,3};
  Integer[] blankArray = {};

  private MyArrayList list1, list2, list3, list4, list5, list6, list7;

  /**
   * Sets up lists before each @Test
   *
   */
  @Before
  public void setUp() throws Exception {
    list1 = new MyArrayList();
    list2 = new MyArrayList(DEFAULT_CAPACITY);
    list3 = new MyArrayList(MY_CAPACITY);
    list4 = new MyArrayList(arr);
    list5 = new MyArrayList<Integer>(arrInts);
    list6 = new MyArrayList<Integer>(blankArray); //has a capacity of 0.
    list7 = new MyArrayList(null);
  }

  /**
   * Tests constructor throwing an exception
   */
  @Test
  public void testInvalidConstructor() {
    try {
      MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  /**
   * Tests that constructor correctly assigns size values.
   */
  @Test
  public void testDefaultSize() {
    assertEquals("Check size for default constructor", 0, list1.size());
    assertEquals("Check size for constructor with given capacity of 10",
      0, list2.size());
    assertEquals("Check size for constructor with given capacity of 3",
      0, list3.size());
    assertEquals("Check size for constructor with given array",
      10, list4.size());
    assertEquals("Check size for constructor with given int array",
      3, list5.size());
    assertEquals("Check size for constructor with null array",
      0, list7.size());
  }

  /**
   * Tests that underlying array is the correct size
   */
  @Test
  public void testInitialCapacity() {
    assertEquals("Check default capacity",
      DEFAULT_CAPACITY, list1.getCapacity());
    assertEquals("Check given capacity",
      MY_CAPACITY, list3.getCapacity());
    assertEquals("Check null array capacity",
      DEFAULT_CAPACITY, list7.getCapacity());
  }

  /**
   * Tests checkCapacity correctly expands array, if necessary
   */
  @Test
  public void testCheckCapacity()
  {
    //list1 initially has capacity 10.
    list1.checkCapacity(11);
    assertEquals("Capacity should be double default capacity",
    DEFAULT_CAPACITY * 2, list1.getCapacity());

    list2.checkCapacity(25);
    assertEquals("Capacity should be 25", 25, list2.getCapacity());

    list3.checkCapacity(1);
    assertEquals("Capacity should remain at 3",
      MY_CAPACITY, list3.getCapacity());

    list6.checkCapacity(1);
    assertEquals("Capacity should be 10",
      DEFAULT_CAPACITY, list6.getCapacity());
  }

  /**
   * Tests various cases of get()
   */
  @Test
  public void testGet()
  {
    assertEquals("Test Get", 1, list5.get(0));
    assertEquals("Test Get", 2, list5.get(1));
    assertEquals("Test Get", null, list4.get(1));

    try{
        list5.get(100);
        fail("Expected IndexOutOfBoundsException");
    }catch (IndexOutOfBoundsException e) {
      // pass
    }

    try{
        list5.get(-100);
        fail("Expected IndexOutOfBoundsException");
    }catch (IndexOutOfBoundsException e) {
      // pass
    }
  }

  /**
   * Tests various cases of remove()
   */
  @Test
  public void testRemove()
  {
      String[] worldCities = {"NYC", "Berlin", "Shanghai", "Tokyo", "London"};
      list4 = new MyArrayList<String>(worldCities);
      assertEquals("Test return value", "Berlin", list4.remove(1));
        //list4 --> {"NYC", "Shanghai", "Tokyo", "London"}
      assertEquals("Elements have shifted properly", "NYC", list4.get(0));
      assertEquals("Elements have shifted properly", "Shanghai", list4.get(1));
      assertEquals("Elements have shifted properly", "Tokyo", list4.get(2));
      assertEquals("Elements have shifted properly", "London", list4.get(3));
      assertEquals("Size should decrease by 1", 4, list4.size());
      assertEquals("Capacity is unchanged", 5, list4.getCapacity());

      try{
          list4.remove(-100);
          fail("Expected IndexOutOfBoundsException");
      }catch (IndexOutOfBoundsException e) {
        // pass
      }

      try{
          list4.remove(12345);
          fail("Expected IndexOutOfBoundsException");
      }catch (IndexOutOfBoundsException e) {
        // pass
      }
  }

  /**
   * Tests various cases of append()
   */
  @Test
  public void testAppend() {
    int[] nums = {2,4};
    list1.append(nums[0]);
    assertEquals("Check that append increments size", 1, list1.size());
    list1.append(nums[1]);
    assertEquals("Check that capacity is unchanged",
      DEFAULT_CAPACITY, list1.getCapacity());

  }

  /**
   * Tests various cases of prepend()
   */
  @Test
  public void testPrepend()
  {
    list2 = new MyArrayList<Integer>(blankArray);
    list2.prepend(123);
    assertEquals(123, list2.get(0));

    String[] holidays = {"Christmas", "4th of July", "Thanksgiving"};
    list2 = new MyArrayList<String>(holidays);
    list2.prepend("MLK Day");
    list2.prepend("Memorial Day");
    list2.prepend("Veterans Day");
      //list2 --> {"Veterans Day", "Memorial Day", "MLK Day",
      //   "Christmas", "4th of July", "Thanksgiving"}
    assertEquals("Veterans Day is index 0", "Veterans Day", list2.get(0));
    assertEquals("Test elements have shifted" , "4th of July", list2.get(4));
  }

  /**
   * Tests various cases of insert()
   */
  @Test
  public void testInsert()
  {
     String[] cities = {"NYC", "LA", "Boston", "Chicago", "Dallas", "Atlanta"};
     list2 = new MyArrayList<String>(cities);
     list2.insert(2,"Orlando");
      //list2 -->
      //{"NYC", "LA", "Orlando", "Boston", "Chicago", "Dallas", "Atlanta"}
     assertEquals("capacity has doubled", 12, list2.getCapacity());
     assertEquals("size has increased by 1", 7, list2.size());
     assertEquals("index 1 remains the same", "LA", list2.get(1));
     assertEquals("index 2 is changed", "Orlando", list2.get(2));
     assertEquals("rest of the array has shifted", "Boston", list2.get(3));
     assertEquals("rest of the array has shifted", "Dallas", list2.get(5));
     assertEquals("rest of the array has shifted", "Atlanta", list2.get(6));

     list2.insert(4, "Philadelphia");
      //list2 -->
      //{"NYC", "LA", "Orlando", "Boston", "Philadelphia",
      // "Chicago", "Dallas", "Atlanta"}
     assertEquals("Capacity remains the same", 12, list2.getCapacity());
     assertEquals("size increases by 1", 8, list2.size());

     try{
         list2.insert(100, "Berlin");
         fail("Expected IndexOutOfBoundsException");
     }catch (IndexOutOfBoundsException e) {
       // pass
     }

     try{
         list2.insert(-2, "Tokyo");
         fail("Expected IndexOutOfBoundsException");
     }catch (IndexOutOfBoundsException e) {
       // pass
     }
  }

  /**
   * Tests various cases of set().
   */
  @Test
  public void testSet()
  {
    String[] presidents ={"Washington", "Adams", "Jefferson", "Madison", "JQA"};
    list3 = new MyArrayList<String>(presidents);
    list3.set(4, "Monroe");
      //list3 --> {"Washington", "Adams", "Jefferson", "Madison", "Monroe"};
    assertEquals("size should be the same", 5, list3.size());
    assertEquals("cap. should be the same", 5, list3.getCapacity());
    assertEquals("4th index is changed", "Monroe", list3.get(4));

    try{
        list3.set(100, "Tom Cruise");
        fail("Expected IndexOutOfBoundsException");
    }catch (IndexOutOfBoundsException e) {
      // pass
    }

    try{
        list3.set(-1, "Harrison Ford");
        fail("Expected IndexOutOfBoundsException");
    }catch (IndexOutOfBoundsException e) {
      // pass
    }
  }

  /**
   * Tests various cases of trimToSize()
   */
  @Test
  public void testTrimToSize()
  {
    list5 = new MyArrayList<Integer>(blankArray); //capacity 0, size 0
    list5.checkCapacity(10); //capacity 10, size 0
    list5.trimToSize(); //capacity is now 0
    assertEquals(0, list5.getCapacity());

    Integer[] oneToTen = {1,2,3,4,5,6,7,8,9,10};
    list5 = new MyArrayList<Integer>(oneToTen); //capacity 10, size 10
    list5.trimToSize(); //capacity remains 10
    assertEquals(10, list5.getCapacity());

    list5 = new MyArrayList<Integer>(arrInts);
    list5.checkCapacity(10); //capacity 10, size 3;
    list5.trimToSize(); //capacity is now 3
    assertEquals(3,list5.getCapacity());
  }
}
