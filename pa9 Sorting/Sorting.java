/**
 * Generic Sorting Class that extends the Comparable<E> interface that allows
 * for sorting. Can sort with the insertion sort algorithm or the merge sort
 * algorithm and will print out iterations of the array.
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs15lsp20aum
 * EMAIL: erjin@ucsd.edu
 * Sources: Psudeocode for both sorting methods adapted from ZyBooks.
 *          ZyBooks Ch 14.3 (insertion sort) & 15.2 (merge sort)
 */

import java.util.Arrays;

/**
 * Generic sorting class that extends comparable. Has methods for insertion sort
 * merge sort and a merge sort helper method, along with main for testing.
 *
 */
public class Sorting<E extends Comparable<E>>{

    private static final int SPLIT_FOUR = 4;
    private static final int SPLIT_HALF = 2;

     /**
      * Sorts and prints the array via the insertion sort algorithm.
      * @param array - array to be sorted
      * @throws NullPointerException if array is null
      *                  or any element in it is null
      */
     public void insertionSort(E[] array)
     {
        if(array == null)
            throw new NullPointerException();

        for(E item: array)
        {
            if(item == null)
                throw new NullPointerException();
        }

        if(array.length == 1 || array.length == 0)
           return;


        for(int i = 0; i < array.length; i++)
        {
            int j = i;
            while(j > 0 && array[j].compareTo(array[j - 1]) < 0)
            {
                E temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j--;
            }
            System.out.println(Arrays.toString(array));
        }
     }


     /**
      * Recursively sorts and prints the array via the merge sort algorithm.
      * The algorithm splits the array into length 1, then merges and sorts them
      * Will split the array into 4 parts and recursively sort, unless the array
      * has length less than 4, then the array is split in half instead.
      * @param array - array to be sorted
      * @throws NullPointerException if array is null
      *                  or any element in it is null
      */
     public void mergeSort(E[] array) 
     {
         if(array == null)
            throw new NullPointerException();
         for(E item: array)
         {
             if(item == null)
                throw new NullPointerException();
         }
         if(array.length == 1 || array.length == 0)
            return;

         int dividePoint = 0;

         if(array.length < SPLIT_FOUR)
            dividePoint = array.length/SPLIT_HALF;
         else
            dividePoint = array.length/SPLIT_FOUR;

        E[] leftPart = Arrays.copyOfRange(array, 0, dividePoint);
        E[] rightPart = Arrays.copyOfRange(array, dividePoint, array.length);

        if(array.length > 1)
        {
            mergeSort(leftPart);
            mergeSort(rightPart);
            merge(array, leftPart, rightPart, 0, dividePoint);
            System.out.println(Arrays.toString(array));
        }
        else
        {
            merge(array, leftPart, rightPart, 0, dividePoint);
        }
     }

     /**
      * Helper method for the mergeSort method. Takes two arrays that are
      * already sorted and merges them into a single, larger, sorted array.
      * @param array - large array to be sorted
      * @param leftArray - left array to be merged
      * @param rightArray - right array to be merged
      * @param left - starting index of left array
      * @param right - starting index of right array
      */
     public void merge(E[]array,E[] leftArray,E[] rightArray,int left,int right)
     {

        int pointerL = left;
        int pointerR = right;

        for(int i = 0; i < array.length; i++)
        {
            if(pointerL >= leftArray.length) //if leftArray is empty
            {
                array[i] = rightArray[pointerR - right];
                pointerR++;
            }

            else if(pointerR - right >= rightArray.length)//if right is empty
            {
                array[i] = leftArray[pointerL];
                pointerL++;
            }
            else if(leftArray[pointerL].
                    compareTo(rightArray[pointerR - right]) <= 0)
            {
                array[i] = leftArray[pointerL];
                pointerL++;
            }
            else
            {
                array[i] = rightArray[pointerR - right];
                pointerR++;
            }
        }

     }

     /**
      * Main method for testing
      */
     public static void main(String[] args)
     {
         Sorting<Integer> yipee = new Sorting<>();
         Integer[] yea =
            {12, 1, 3, 15, 8, 9, 4, 16, 0, 2, 5, 6, 7, 11, 13, 22, 18};
         yipee.insertionSort(yea);
         yipee.mergeSort(yea);
         Sorting<String> yahoo = new Sorting<>();
         String[] woo =
            {"zulu", "yankee", "golf", "foxtrot", "charlie", "bravo", "kilo"};
         yahoo.mergeSort(woo);
         String[] zerohero = new String[0];
         yahoo.mergeSort(zerohero);
         yahoo.insertionSort(zerohero);
         String[] tiny = {"its only me"};
         yahoo.mergeSort(tiny);
         yahoo.insertionSort(tiny);
         System.out.println("DONE");

     }

}
