/**
 * Class MyStack is an implementation of the LIFO Stack ADT. It uses MyDeque as
 * the underlying data structure and for many of the methods.
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 */

class MyStack<E> implements StackInterface<E>
{
    MyDeque<E> theStack;

    /**
     * Constructor initializes the underlying MyDeque with a certain capacity
     * @throws IllegalArgumentException if input < 0
     */
    public MyStack(int initialCapacity)
    {
        //no need to throw exceptions as the MyDeque constructor throws it
        theStack = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks whether or not the stack is empty
     * @return true if there are no elements in the stack, false otherwise
     */
    public boolean empty()
    {
        return theStack.size() == 0;
    }

    /**
     * Adds the specified element to the top of the stack.
     * @param element the element to be added
     * @throws NullPointerException if the specified element is null.
     */
    public void push(E element)
    {
        theStack.addFirst(element);
    }

    /**
     * Removes and returns the element at the top of the stack
     * @return the element removed, or null if the size was zero.
     */
    public E pop()
    {
        return theStack.removeFirst();
    }

    /**
     * Returns but does not remove the element at the top of the stack
     * @return the element at the head, or null if the size was zero.
     */
    public E peek()
    {
        return theStack.peekFirst();
    }
}
