/**
 * Class MyQueue is an implementation of the FIFO Queue ADT. It uses MyDeque as
 * the underlying data structure and for many of the methods.
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 */

 class MyQueue<E> implements QueueInterface<E> {
     MyDeque<E> theQueue;

     /**
      * Constructor initializes the underlying MyDeque with a certain capacity
      * @throws IllegalArgumentException if input < 0.
      */
     public MyQueue(int initialCapacity)
     {
         //no need to throw exception here as the MyDeque constructor throws it
         theQueue = new MyDeque<E>(initialCapacity);
     }

     /**
      * Checks whether or not the queue is empty
      * @return true if there are no elements in the queue, false otherwise
      */
     public boolean empty()
     {
         return theQueue.size() == 0;
     }

     /**
      * Adds the specified element to the end of the queue.
      * @param element the element to add to the queue
      * @throws NullPointerException if the specified element is null.
      */
     public void enqueue(E element)
     {
         theQueue.addLast(element);
     }

     /**
      * Removes and returns the element at the front of the queue.
      * @return the element removed, or null if the size was zero.
      */
     public E dequeue()
     {
         return theQueue.removeFirst();
     }

     /**
      * Returns but does not remove the element at the front of the queue.
      * @return the element at the head, or null if the size was zero.
      */
     public E peek()
     {
         return theQueue.peekFirst();
     }
 }
