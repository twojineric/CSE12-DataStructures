/**
 * Class MyDeque is a generic class that follows the Java Deque ADT - very
 * similar to the Queue ADT, but with the added ability to add/remove objects
 * at the both ends. It stores these objects in an underlying array
 * that is dynamically resized when necessary. These objects are casted to
 * generic type E if needed.
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 */

 class MyDeque<E> implements DequeInterface<E> {

     Object[] data; //underlying array
     int size;  //number of elements
     int rear;  //index of the last item
     int front; //index of the first item
     private static final int DEFAULT_SIZE = 10;
     private static final int CONSTANT_2 = 2;

     /**
      * Initializes the underlying array to an array with size initialCapacity
      * @throws IllegalArgumentException if input < 0.
      */
     public MyDeque(int initialCapacity)
     {
        if(initialCapacity < 0)
            throw new IllegalArgumentException();
        else
            data = new Object[initialCapacity];

     }

     /**
      * Returns the number of elements.
      * @return number of elements in this MyDeque
      */
     public int size()
     {
         return size;
     }


     /**
      * Doubles the capacity of the deque and maintains the same elements.
      * Element order is altered such that the element at index front is now
      * moved to index 0. Variables front and rear are reassigned accordingly.
      * If capacity is 0, set to 10 (DEFAULT_SIZE)
      */
     public void expandCapacity()
     {
         //if size is 0, expand size to 10.
         if(size == 0)
         {
             data = new Object[DEFAULT_SIZE];
             front = 0;
             rear = 0;
         }
         else //double the current size
         {
             int newLength = CONSTANT_2 * data.length;
             Object[] expandedArr = new Object[newLength];

             int offset = 0;
             //moves items in array such that the front is at index 0
             for(int i = front; i != rear; i++)
             {

                 expandedArr[offset] = data[i];
                 offset++;

                 if(i == data.length - 1)
                    i = -1; //loop this back to the front of data, then loop i++
             }
             //the loop skips the element at index rear, so we set it here
             expandedArr[offset] = data[rear];
             //reassign variables
             front = 0;
             rear = size - 1;
             data = expandedArr;
         }
     }

     /**
      * Adds the specified element to the front of the deque.
      *
      * @param element the element to add to the front of the deque
      * @throws NullPointerException if input is null.
      */
     public void addFirst(E element)
     {
         if(element == null)
            throw new NullPointerException();
         //this is only called if this is the first element that will be
         //inserted into an empty array.
         //That one (non null) element will become both the front and rear
         else if(rear == 0 && front == 0 && data[front] == null)
         {
             data[front] = element;
             size++;
         }
         else
         {
             if(size == data.length)
                expandCapacity();

             //if the current front is at index 0,
             //the next item inserted will go to the back of the array
             if(front == 0)
             {
                front = data.length - 1;
                data[front] = element;
             }
             //if front is not at index 0,
             //the inserted element will go one index before
             else
             {
                front = front - 1;
                data[front] = element;
             }
             size++;
         }
     }

     /**
      * Adds the specified element to the back of the deque.
      *
      * @param element the element to add to the back of the deque
      * @throws NullPointerException if input is null.
      */
     public void addLast(E element)
     {
         if(element == null)
            throw new NullPointerException();
         //this is only called if this is the first element that will be
         //inserted into an empty array.
         //That one (non null) element will become both the front and rear
         else if(rear == 0 && front == 0 && data[front] == null)
         {
             data[front] = element;
             size++;
         }
         else
         {
             if(size == data.length)
                expandCapacity();

             //if the current rear is at the end of the array
             //the next item goes at the front (index 0)
             if(rear == data.length - 1)
             {
                rear = 0;
                data[rear] = element;
             }
             //if rear is not the end of the array, the last item inserted
             //goes one index after.
             else
             {
                rear = rear + 1;
                data[rear] = element;
             }
             size++;
         }
     }

     /**
      * Removes and returns the element at the front of the deque.
      * @return the element removed, or null if the deque is empty.
      */
     public E removeFirst()
     {
         if(size == 0)
            return null;
         else
         {
            E firstElement = (E) data[front];
            data[front] = null; //remove the element from array
            size--;

            if(front == data.length - 1)
                front = 0;
            else
                front = front + 1;

            return firstElement;
         }
     }

     /**
      * Removes and returns the element at the rear of the deque.
      * @return the element removed, or null if the deque is empty.
      */
     public E removeLast()
     {
         if(size == 0)
            return null;
         else
         {
             E lastElement = (E) data[rear];
             data[rear] = null; //remove the element from array
             size--;

             if(rear == 0)
                rear = data.length - 1;
             else
                rear = rear - 1;

             return lastElement;
         }
     }

     /**
      * Returns the first element in the deque without removing it
      * @return the element at the front, or null if the deque is empty.
      */
     public E peekFirst()
     {
         if(size == 0)
            return null;
         else
            return (E) data[front];
     }

     /**
      * Returns the last element in the deque without removing it.
      * @return the element at the back, or null if the deque is empty.
      */
     public E peekLast()
     {
         if(size == 0)
            return null;
         else
            return (E) data[rear];
     }

 }
