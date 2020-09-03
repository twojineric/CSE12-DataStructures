

// With generics
public interface MyList<E> {
    /* Increase the capacity of underlying array if needed */
    void checkCapacity(int requiredCapacity);

    /* Get the amount of elements array can hold */
    int getCapacity();

    /* Add an element at the specified index */
    void insert(int index, E element);

    /* Add an element to the beginning of the list */
    void append(E element);

    /* Add an element to the end of the list */
    void prepend(E element);

    /* Get the element at the given index */
    E get(int index);

    /* Replaces an element at the specified index with a new element and return the original elements */
    E set(int index, E element);

    /* Get the number of elements in the list */
    int size();

    /* Remove the element at the specified index and return the removed element*/
    E remove(int index);
}
