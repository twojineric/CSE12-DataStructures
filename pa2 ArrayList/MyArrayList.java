
/**
 * Class MyArrayList is a generic class that implements some of the same methods
 * that are used in java.util.ArrayList. It stores Objects in an underlying
 * array and dynamically resizes as needed. These objects are then casted to
 * the specific generic type E when needed.
 *
 *  NAME: Eric Jin
 *  ID: A15816483
 *  EMAIL: erjin@ucsd.edu
 *
 */
public class MyArrayList<E> implements MyList<E> {

    Object[] data;
    int size;
    private static final int DEFAULT_LENGTH = 10;

    /**
    * No args constructor
    * Initializes data to length 10.
    */
    public MyArrayList()
    {
        data = new Object[DEFAULT_LENGTH];
    }

    /**
     *
     * @param initialCapacity - capacity of the array
     * throws exception if initialCapacity is less than 0.
     */
    public MyArrayList(int initialCapacity)
    {
        if(initialCapacity < 0)
            throw new IllegalArgumentException("Capacity must be > 0");
        else
            data = new Object[initialCapacity];
    }

    /**
     *
     * @param arr - Initializes with a given array
     * If arr is null, initialize with the same behavior as default constructor
     */
    public MyArrayList(E[] arr)
    {
        if(arr == null)
            data = new Object[DEFAULT_LENGTH];
        else
        {
            //if not null, then transfer each Object from arr to data
            data = new Object[arr.length];
            size = arr.length;
            for(int i = 0; i < data.length; i++)
            {
                data[i] = arr[i];
            }
        }

    }

    /**
     * If necessary, increases the size of the underlying array.
     *
     * "first check"
     * Checks if current arr has as much capacity as requiredCapacity
     * If not, double (x2) the current capacity. If capacity is 0, set to 10.
     *
     * If that's still not enough, set the arr capacity to requiredCapacity.
     * @param requiredCapacity
     * @return void
     */
    public void checkCapacity(int requiredCapacity)
    {
        if(this.data.length >= requiredCapacity)
        //if the capacity is sufficient, do nothing
        {
            return;
        }
        else if(this.data.length == 0)
        {
            if(DEFAULT_LENGTH * 2 < requiredCapacity)
            //this if statement is executed if the "first check" has
            //insufficient size, so the capacity is set to requiredCapacity
            {
                Object[] expandedArr = new Object[requiredCapacity];
                data = expandedArr;
            }
            else
            //this statement is executed if the required capacity is under 10
            //and therefore it passes the "first check"
            {
                Object[] expandedArr = new Object[DEFAULT_LENGTH];
                data = expandedArr;
            }
        }
        else //if length of underlying array is not 0.
        {
            if(this.data.length * 2 < requiredCapacity)
            //this if statement is executed if the "first check" has
            //insufficient size, so the capacity is set to requiredCapacity
            {
                Object[] expandedArr = new Object[requiredCapacity];
                //moves all items from old array into new one.
                for(int i = 0; i < data.length; i++)
                {
                    expandedArr[i] = data[i];
                }
                data = expandedArr;
            }
            else
            {
            //this statement is executed if the required capacity is under 10
            //and therefore it passes the "first check", so the length of the
            //new array is simply double the old arr length.
                Object[] expandedArr = new Object[data.length * 2];
                //moves all items from old array into new one.
                for(int i = 0; i < data.length; i++)
                {
                    expandedArr[i] = data[i];
                }
                data = expandedArr;
            }
        }
    }

    /**
     * Returns the length of the underlying array data.
     * @return - length of underlying array
     */
    public int getCapacity()
    {
        return this.data.length;
    }

    /**
     * Get the number of elements in the list
     * @return - size (num of elements)
     */
    public int size()
    {
        return this.size;
    }

    /**
     * inserts element E at the specified index
     *
     * @param index
     * @param element
     * @return void
     */
    public void insert(int index, E element)
    {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        else
        {
          checkCapacity(size + 1);
          //shift all the elements forward by 1.
          for(int i = size; i > index; i--)
          {
              data[i] = data[i - 1];
          }
          data[index] = element;
          size++;
        }
    }

    /**
     * Adds element to the end of the list
     * @param element
     * @return void
     */
    public void append(E element)
    {
        checkCapacity(size + 1);
        data[size] = element;
        size++;
    }

    /**
     * Adds element to the beginning of the list
     *
     * @param element
     * @return void
     */
     public void prepend(E element)
     {
        checkCapacity(size + 1);
        //shifts all elements in data forward by 1
        for(int i = size; i > 0; i--)
        {
            data[i] = data[i - 1];
        }
        data[0] = element;
        size++;
     }

    /**
     * @return E, the element at the specified index
     *
     * @param index
     * @return element at index
     */
    public E get(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else
            return (E) data[index];
    }

    /**
     * Replaces element at the index and returns the original, removed element
     *
     * @param index
     * @param element - element to replace
     * @return removed element
     */
    public E set(int index, E elememt)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        else
          {
            E toReturn = (E) data[index];
            data[index] = elememt;
            return toReturn;
          }
    }

    /**
     * Removes and returns the element at the specified index
     * @param index
     * @return the element removed from the specified index
     */
    public E remove(int index)
    {
      if(index < 0 || index >= size)
          throw new IndexOutOfBoundsException();
      else
        {
          E toReturn = (E) data[index];
          //shifts all the elements in data back by 1.
          for(int i = index; i < size - 1; i++)
          {
              data[i] = data[i + 1];
          }
          size--;
          return toReturn;
        }
    }

    /**
     * Trims capacity of data to the size instance variable.
     * @return void
     */
    public void trimToSize()
    {
       Object[] newArr = new Object[size];
       //creates new array of length size and moves all elements into new array
       for(int i = 0; i < size; i++)
       {
          newArr[i] = data[i];
       }
       data = newArr;
    }
}
