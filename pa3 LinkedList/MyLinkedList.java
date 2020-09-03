
/**
 * This file contains a custom MyLinkedList<E> generic class. The class
 * implements some methods in the AbstractList class. The custom MyLinkedList is
 * a doubly linked list which uses a custom node class and a custom Iterator
 * class. The linked list uses dummy head and tail nodes with null values.
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */
import java.util.*;

/**
 * Class MyLinkedList<E> is a generic doubly linked list that uses methods in
 * the list interface. The class also uses a custom Node class and a custom
 * linked list iterator class.
 *
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int nelems; //num of elements in the list
    Node head;  //null "dummy" head node
    Node tail;  //null "dummy" tail node

    /**
     * Each node has a reference to the previous node and the next node and
     * contains some data. The head and tail nodes have data == null.
     *
     */
    protected class Node {

        E data;
        Node next;
        Node prev;

        /**
         * Constructor creates a node with the argument as data.
         */
        public Node(E element)
        {
            data = element;
        }

        /**
         * @param p - Sets p as the previous node.
         */
        public void setPrev(Node p)
        {
            prev = p;
        }

        /**
         * @param n - Sets n as the next node.
         */
        public void setNext(Node n)
        {
            next = n;
        }

        /**
         * @param e - Set e to the node's data
         */
        public void setElement(E e)
        {
            data = e;
        }

        /**
         * @return - the next node
         */
        public Node getNext()
        {
            return next;
        }

        /**
         * @return - the previous node
         */
        public Node getPrev()
        {
            return prev;
        }

        /**
         * @return - the current node's data
         */
        public E getElement()
        {
            return data;
        }
    }

    /**
     * MyListIterator implementation
     *
     */
    protected class MyListIterator implements ListIterator<E> {

        boolean forward; //direction of the last iterator move
        boolean canRemoveOrSet; //is it legal to call remove() or set()
        Node left, right;
        int idx;

        /**
         * Constructor starts iterator with index 0, the head node is to the
         * left and the first node (LinkedList index 0) to the right.
         *
         */
        public MyListIterator()
        {
            forward = true;
            canRemoveOrSet = false;
            idx = 0;
            left = head;
            right = head.getNext();
        }

        /**
         * Inserts node with data e immediately before the current pointer
         * position.
         *
         * @param e - Node data to be inserted.
         */
        @Override
        public void add(E e)
        {
            if (e == null)
                throw new NullPointerException();
            else
            {
                Node insertedNode = new Node(e);
                //reassign references
                insertedNode.setNext(right);
                insertedNode.setPrev(left);
                left.setNext(right);
                right.setPrev(left);

                left = insertedNode;
                canRemoveOrSet = false;
                idx++;
                nelems++;
            }
        }

        /**
         * @return - if there is a valid node (not the tail) to the iterator's
         * right
         *
         */
        @Override
        public boolean hasNext()
        {
            return right.getElement() != null;
        }

        /**
         *
         * @return - if there is a valid node (not the head) to the iterator's
         * left
         */
        @Override
        public boolean hasPrevious()
        {
            return left.getElement() != null;
        }

        /**
         * Moves the iterator forward one index / over one node.
         *
         * @return E - the data of the Node that was "jumped" over by the
         * iterator.
         */
        @Override
        public E next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            else
            {
                E nextElem = right.getElement();
                left = right;
                right = right.getNext();
                idx++;
                canRemoveOrSet = true;
                forward = true;
                return nextElem;
            }
        }

        /**
         * @return - index of element that would be returned by a call to next()
         * *Does not move the iterator
         */
        @Override
        public int nextIndex()
        {
            return idx;
        }

        /**
         * Moves the iterator back one index / back by one node.
         *
         * @return E - the data of the Node that was "jumped" over by the
         * iterator
         */
        @Override
        public E previous()
        {
            if (!hasPrevious())
                throw new NoSuchElementException();
            else
            {
                E prevElem = left.getElement();
                right = left;
                left = left.getPrev();
                idx--;
                canRemoveOrSet = true;
                forward = false;
                return prevElem;
            }
        }

        /**
         * @return - index of element that would be returned by a call to
         * previous()
         * *Does not move the iterator
         */
        @Override
        public int previousIndex()
        {
            return idx - 1;
        }

        /**
         * Removes the last element that was returned by the most recent
         * next()/previous() call. Cannot be called right after calling add().
         *
         */
        @Override
        public void remove()
        {
            if (!canRemoveOrSet)
                throw new IllegalStateException();
            else
            {
                if (forward)
                {
                    Node nodeToRemove = left;
                    left = nodeToRemove.getPrev();
                    left.setNext(right); //reassign references
                    right.setPrev(left);

                    nodeToRemove.setNext(null); //remove node access to list
                    nodeToRemove.setPrev(null);
                    idx--;
                }
                else
                {
                    Node nodeToRemove = right;
                    right = nodeToRemove.getNext();
                    left.setNext(right); //reassign references
                    right.setPrev(left);

                    nodeToRemove.setNext(null); //remove node access to list
                    nodeToRemove.setPrev(null);
                }
                canRemoveOrSet = false;
                nelems--;
            }
        }

        /**
         * Sets the value of the node returned by the most recent
         * next()/previous() call to the new value e. Cannot be called right
         * after remove() or add()
         *
         * @param e
         */
        @Override
        public void set(E e)
        {
            if (e == null)
                throw new NullPointerException();
            else if (!canRemoveOrSet)
                throw new IllegalStateException();
            else
            {
                if (forward)
                    left.setElement(e);
                else
                    right.setElement(e);
            }
        }
    }

    /**
     * BEGIN implementation of the MyLinkedList Class. Creates empty linked list
     * with "dummy" head/tail nodes.
     */
    public MyLinkedList()
    {
        //create a head and tail dummy node
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        head.setPrev(null);
        tail.setNext(null);
        tail.setPrev(head);
    }

    /**
     * @return - number of elements in the list
     */
    @Override
    public int size()
    {
        return nelems;
    }

    /**
     * Gets data located at node with position index.
     *
     * @param index
     * @return - data at index
     */
    @Override
    public E get(int index)
    {
        if (index < 0 || index >= nelems)
            throw new IndexOutOfBoundsException();
        else
        {
            Node targetNode = getNth(index);
            return targetNode.getElement();
        }
    }

    /**
     * Inserts a new element data at position index.
     *
     * @param index
     * @param data
     */
    @Override
    public void add(int index, E data)
    {
        if (index < 0 || index > nelems)
            throw new IndexOutOfBoundsException();
        else if (data == null)
            throw new NullPointerException();
        else
        {
            if (index == nelems)
                add(data);
            else
            {
                Node insertedNode = new Node(data); //node to be inserted
                Node nextNode = getNth(index); //node after inserted node
                Node prevNode = nextNode.getPrev(); //node before inserted node

                prevNode.setNext(insertedNode); //reassign references
                insertedNode.setPrev(prevNode);
                insertedNode.setNext(nextNode);
                nextNode.setPrev(insertedNode);
                nelems++;
            }
        }
    }

    /**
     * Appends data to the end of the list
     *
     * @param data - element to be appended
     * @return true
     *
     */
    public boolean add(E data)
    {
        if (data == null)
            throw new NullPointerException();
        else
        {
            Node insertedNode = new Node(data); //node to be inserted
            Node prevNode = tail.getPrev(); //node before the inserted node

            prevNode.setNext(insertedNode); //reassign references
            insertedNode.setPrev(prevNode);
            insertedNode.setNext(tail);
            tail.setPrev(insertedNode);
            nelems++;
            return true;
        }
    }

    /**
     * Sets the element at position index to data and returns the old element.
     *
     * @param index
     * @param data
     * @return - element originally at index
     */
    public E set(int index, E data)
    {
        if (index < 0 || index >= nelems)
            throw new IndexOutOfBoundsException();
        else if (data == null)
            throw new NullPointerException();
        else
        {
            Node targetNode = getNth(index);
            Node prevNode = targetNode.getPrev();
            Node nextNode = targetNode.getNext();

            Node replacementNode = new Node(data);

            prevNode.setNext(replacementNode); //reassign references
            nextNode.setPrev(replacementNode);
            replacementNode.setNext(nextNode);
            replacementNode.setPrev(prevNode);

            targetNode.setNext(null); //remove removed node's access to list
            targetNode.setPrev(null);
            return targetNode.getElement();
        }
    }

    /**
     * Removes and returns the element at position index
     *
     * @param index
     * @return - removed element
     */
    public E remove(int index)
    {
        if (index < 0 || index >= nelems)
            throw new IndexOutOfBoundsException();
        else
        {
            Node targetNode = getNth(index);
            Node prevNode = targetNode.getPrev();
            Node nextNode = targetNode.getNext();

            prevNode.setNext(nextNode); //reassign references
            nextNode.setPrev(prevNode);
            nelems--;

            targetNode.setNext(null); //remove removed node's access to list
            targetNode.setPrev(null);
            return targetNode.getElement();
        }

    }

    /**
     * Clears and removes all elements/nodes from the list
     *
     * @return void
     */
    public void clear()
    {
        head.setNext(tail); //set head and tail node references to each other
        head.setPrev(null); //garbage collection removes the rest of the list
        tail.setNext(null);
        tail.setPrev(head);
        nelems = 0;
    }

    /**
     * Checks if list has nodes.
     *
     * @return - if list is empty return true, else return false
     */
    public boolean isEmpty()
    {
        return nelems == 0;
    }

    /**
     * Returns the Node that is at position index in the list
     *
     * @param index
     * @return - reference to the node at position index
     */
    protected Node getNth(int index)
    {
        if (index < 0 || index >= nelems)
            throw new IndexOutOfBoundsException();
        else
        {
            Node target = head.getNext();
            //iterate through list until index node is reached
            for (int i = 0; i < index; i++)
            {
                target = target.getNext();
            }
            return target;
        }
    }

    /**
     * @return MyListIterator
     */
    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }

    /**
     * @return MyListIterator
     */
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }

}
