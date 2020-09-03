/**
 * Implementing an ArrayList based minimum heap. Child objects are always
 * greater than their parent nodes.  This heap only accepts generic
 * objects that extend the Comparable interface, so the compareTo() method
 * is used to determine the final ordering of the array.
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * MyMinHeap uses an arrayList to keep track of the items in the heap.
 *
 */
public class MyMinHeap<E extends Comparable<E>> {

    protected List<E> list;

    /**
     * Initializes list to an empty arrayList
     */
    public MyMinHeap()
    {
        list = new ArrayList<>();
    }

    /**
     * Passes in collection to ArrayList constrcutor, then calls
     * percolateDown() method to order the heap.
     * @throws NullPointerException if collection or any element in collection
     *                              is null
     */
    public MyMinHeap(Collection<? extends E> collection)
    {
        if(collection == null)
            throw new NullPointerException();
        else
        {
            for(E item : collection)
            {
                if(item == null)
                    throw new NullPointerException();
            }
        }
        list = new ArrayList<>(collection);

        for(int i = list.size() - 1; i >= 0; i--)
        {
            percolateDown(i);
        }
    }

    /**
     * Swaps the elements in the two given indicies.
     * @param from swaps the element at this index with the element at to.
     * @param to swaps the element at this index with the element at from.
     */
    protected void swap(int from, int to)
    {
        E temp = list.get(from);
        list.set(from, list.get(to));
        list.set(to, temp);
        return;
    }

    /**
     * Returns the index of the parent node of input index
     * @param index parent node index
     * @return index of parent node
     */
    protected int getParentIdx(int index)
    {
        if(index == 0)
            return 0;
        else
            return (index - 1)/2;
    }

    /**
     * Returns the index of the left child node of input index.
     * This method assumes that input index is greater than 0.
     * @param index parent node index
     * @return index of left child node
     */
    protected int getLeftChildIdx(int index)
    {
        return index * 2 + 1;
    }

    /**
     * Returns the index of the right child node of input index.
     * This method assumes that input index is greater than 0.
     * @param index parent node index
     * @return index of right child node
     */
    protected int getRightChildIdx(int index)
    {
        return index * 2 + 2;
    }

    /**
     * Given the index of a parent node, returns the index of the smaller of
     * the two child nodes. If the two nodes are equal,
     * return the index of the left node. If node has no children, return -1.
     * This method assumes that the input index is greater than 0.
     * @param index the parent index
     * @return the smaller of the two children, or -1 if a node has no children
     */
    protected int getMinChildIdx(int index)
    {
        //node has no children
        if(getLeftChildIdx(index) >= list.size())
            return -1;

        try {
            E rightChild = list.get(getRightChildIdx(index));
        } catch (IndexOutOfBoundsException e){
            return getLeftChildIdx(index); //node only has one child
        }

        E leftChild = list.get(getLeftChildIdx(index));
        E rightChild = list.get(getRightChildIdx(index));

        //node has two children, we determine and return the smaller one.
        if(rightChild.equals(leftChild))
            return getLeftChildIdx(index);
        else if(rightChild.compareTo(leftChild) > 0)
            return getLeftChildIdx(index);
        else
            return getRightChildIdx(index);

    }

    /**
     * Takes the element at index and continues to swap it with its parent
     * until heap properties are satisfied (element >= parent)
     * This method assumes that index is within bounds of the underlying array
     * @param index index of element to percolate upwards
     */
    protected void percolateUp(int index)
    {
        if(index == 0)
            return;

        E parent = list.get(getParentIdx(index));
        E child = list.get(index);

        if(child.compareTo(parent) >= 0)
            return;
        else //recursive step
        {
            swap(getParentIdx(index), index);
            percolateUp(getParentIdx(index));
        }
    }

    /**
     * Takes element at index and continues to swap it with its smaller child
     * until heap properties are satisfied (child >= element)
     * This method assumes that index is within bounds of the underlying array
     * @param index index of element to percolate downwards
     */
    protected void percolateDown(int index)
    {
        if(index == list.size() - 1)
            return;
        if(index == -1)
            return;

        E parent = list.get(index);

        try{
            E smallerChild = list.get(getMinChildIdx(index));
        } catch (IndexOutOfBoundsException e) {
            return;
        }

         //assume that a child exists
         E smallerChild = list.get(getMinChildIdx(index));

        if(smallerChild.compareTo(parent) >= 0)
            return;
        else //recursive step
        {
            int initialIndex = getMinChildIdx(index);
            swap(initialIndex, index);
            percolateDown(initialIndex);
        }
    }

    /**
     * Deletes the item at index, fills it with the item at the end of the array
     * and percolates to fix the tree.
     * This method assumes that the element at the specified index exists.
     * @param index the index of the element to be deleted
     * @return the deleted element
     */
    protected E deleteIndex(int index)
    {
        swap(index, list.size() - 1);
        E removedElem = list.remove(list.size() - 1);

        if(list.size() == 0) //the last element was removed with the above line
            return removedElem;

        if(getMinChildIdx(index) == -1)
            percolateUp(index); //we can use the call to index here because the
                               //element at the end of the array has replaced it

        E minChildElem = list.get(getMinChildIdx(index));
        E parentElem = list.get(getParentIdx(index));
        E replacedElem = list.get(index);

        if(parentElem.compareTo(replacedElem) >= 0)
            percolateUp(index);
        if(minChildElem.compareTo(replacedElem) < 0)
            percolateDown(index);

        return removedElem;
    }

    /**
     * Inserts the item at the end of the heap and percolates to fix the order
     * @throws NullPointerException if element is null
     * @param element the element to be inserted.
     */
    public void insert(E element)
    {
        if(element == null)
            throw new NullPointerException();
        else
        {
            list.add(element);
            percolateUp(list.size() - 1);
        }
    }

    /**
     * Returns the root of the heap, which is the smallest element, or null,
     * if the heap is empty.
     * @return the smallest element
     */
    public E getMin()
    {
        if(list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    /**
     * Removes and returns the root of the heap, which is the smallest element,
     * or null, if the heap is empty.
     * @return the smallest element
     */
    public E remove()
    {
        if(list.size() == 0)
            return null;
        else
            return this.deleteIndex(0);
    }

    /**
     * Gets the size of the heap
     * @return the number of elements in the heap
     */
    public int size()
    {
        return list.size();
    }

    /**
     * Clears the heap and removes all elements.
     * The heap is now an empty arraylist.
     */
    public void clear()
    {
        list.clear();
    }
}
