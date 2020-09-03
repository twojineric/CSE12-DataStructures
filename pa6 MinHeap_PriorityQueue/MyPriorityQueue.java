/**
 * MyPriorityQueue is a generic class that uses a Minimum Heap
 * as its underlying data structure.
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */
import java.util.Collection;

 /**
  * Generic class for a priority Queue.
  */
public class MyPriorityQueue<E extends Comparable<E>> {

    protected MyMinHeap<E> heap;

    /**
     * No args constructor initializes to an empty heap
     */
    public MyPriorityQueue()
    {
        heap = new MyMinHeap<>();
    }

    /**
     * Initialzes the heap to a collection.
     * @throws NullPointerException if collection is null or if any element in
     *                              collection is null
     */
    public MyPriorityQueue(Collection<? extends E> collection)
    {
        //no need to throw exceptions because MyMinHeap constructor
        //already throws them for us.
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Inserts the element into the queue, then orders it based on priority.
     * @param element element to be inserted
     */
    public void push(E element)
    {
        if(element == null)
            throw new NullPointerException();
        else
            heap.insert(element);
    }

    /**
     * Returns the element at the front (the one with the highest priority)
     * @return the element at the front of the queue
     */
    public E peek()
    {
        return heap.getMin();
    }

    /**
     * Removes and returns the element at the front (highest priority)
     * @return the element at the front of the queue
     */
    public E pop()
    {
        return heap.remove();
    }

    /**
     * Returns the number of elements in the queue
     * @return number of elements
     */
    public int getLength()
    {
        return heap.size();
    }

    /**
     * Removes all elements from the heap, returns it to an empty heap.
     */
    public void clear()
    {
        heap.clear();
    }

}
