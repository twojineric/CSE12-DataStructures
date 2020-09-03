
/**
* NAME: Eric Jin
* ID: cs12sp20aum
* PID: A15816483
* EMAIL: erjin@ucsd.edu
*
* **I have completed Mid Quarter Feedback**
*/
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable implements MyHashTableInterface {
    //Constant used to double the size and do addition

    final static int CONSTANT_TWO = 2;
    final static double LOADFACTOR_LIMIT = 0.66666667; // 2/3

    LinkedList<String>[] array;//Array that stores linkedlists
    int nelems;     //Number of elements stored in the hash table
    int expand;     //Number of times that the table has been expanded
    int collision;  //Number of collisions since last expansion
    String statsFileName;   //FilePath for the file to write statistics
                            //upon every rehash
    boolean printStats = false; //Boolean to decide whether to write
                                //stats to file or not after rehashing

    /**
    * Constructor makes a hashTable with an inital size.
    *
    * @param size
    */
    public MyHashTable(int size)
    {
        if (size <= 0)
            throw new IllegalArgumentException();
        else
        {
            array = new LinkedList[size];
            printStats = false;
        }
    }

    /**
    * Constructor makes a hashTable with inital size and a file name Certain
    * data are written to the file whenever the table is resized
    *
    * @param size - size of hashTable
    * @param fileName - where to write the expansion data
    */
    public MyHashTable(int size, String fileName)
    {
        if (size <= 0)
            throw new IllegalArgumentException();
        else if (fileName == null)
            throw new NullPointerException();
        else
        {
            array = new LinkedList[size];
            printStats = true;
            statsFileName = fileName;
        }
    }

    /**
    * Inserts the string value into the hashTable
    *
    * @param value - String to be inserted
    * @throws NullPointerException - if value is null
    * @return true if value was inserted, false if it could not be inserted
    */
    @Override
    public boolean insert(String value)
    {
        if (value == null)
            throw new NullPointerException();

        if (contains(value)) //no need to insert because it already exists
            return false;
        else
        {
            double currentLoad = (1.0 * nelems) / array.length;

            if (currentLoad > LOADFACTOR_LIMIT)
            rehash();

            int arrIndex = hashString(value);
            if (array[arrIndex] == null) //linkedList doesnt exist there yet
            {
                LinkedList<String> newBucket = new LinkedList<String>();
                newBucket.add(value);
                array[arrIndex] = newBucket;
            }
            else //there is a collision and chaining is required
            {
                array[arrIndex].add(value);
                collision++;
            }
            nelems++;
            return true;
        }
    }

    /**
    * Deletes the value from the hashTable
    *
    * @param value - String to be removed from hashTable
    * @throws NullPointerException - if value is null
    * @return - true if successfully removed, false if cannot be removed
    */
    @Override
    public boolean delete(String value)
    {
        if (value == null)
            throw new NullPointerException();

        if (!contains(value))
            return false;
        else
        {
            int targetBucket = hashString(value);
            LinkedList<String> targetList = array[targetBucket];

            //to remove the element, we simply remove the linkedList at index
            if (targetList.size() == 1)
            {
                nelems--;
                array[targetBucket] = null;
                return true;
            }
            else //else search the list and remove it
            {
                nelems--;
                targetList.remove(value);
                return true;
            }
        }
    }

    /**
    * Checks if the hashTable contains the given value
    *
    * @param value - check if this is in the hashTable
    * @throws NullPointerException
    * @return true if hashTable contains value, false if it doesn't
    */
    @Override
    public boolean contains(String value)
    {
        if (value == null)
            throw new NullPointerException();

        int searchIndex = hashString(value);
        //go to index and search that LinkedList
        LinkedList<String> searchList = array[searchIndex];

        if (searchList == null)
            return false;

        if (searchList.contains(value))
            return true;
        else
            return false;
    }

    /**
    * Prints the contents of the hashTable
    *
    */
    @Override
    public void printTable()
    {
        for (int i = 0; i < array.length; i++)
        {
            LinkedList<String> bucket = array[i];

            System.out.print(i + ":");
            if (bucket == null)
            {
                System.out.println(""); //print nothing
                continue;
            }

            String elements = "";
            for (int j = 0; j < bucket.size(); j++)
            {
                elements = elements + " " + bucket.get(j) + ",";
            }
            //removes the last comma
            elements = elements.substring(0, elements.length() - 1);
            System.out.println(elements);
        }
        return;
    }

    /**
    * Returns the number of elements in the hashTable
    *
    * @return - number of elements (size)
    */
    @Override
    public int getSize()
    {
        return nelems;
    }

    /**
    * When the load factor reaches > 2/3, this method finds the smallest prime
    * num larger than double the current length. It rehashes all the current
    * items in the hashTable and, if applicable, prints stats to a file.
    *
    */
    @Override
    @SuppressWarnings("unchecked")
    public void rehash()
    {
        //create new array w/ size = smallest prime larger than current len x2
        //save the old array
        LinkedList<String>[] oldArr = array;
        array = new LinkedList[primeGen()];
        //because we use insert(), some variables are going to be messed up,
        //so we save the originals beforehand
        int preRehashElements = nelems;
        int preRehashCollisions = collision;

        //rehash all values
        for (int i = 0; i < oldArr.length; i++)
        {
            LinkedList<String> llist = oldArr[i];

            if (llist == null)
                continue;

            //for each item in each bucket, rehash
            for (int j = 0; j < llist.size(); j++)
            {
                insert(llist.get(j));
            }
        }
        //fix variables that were messed up when calling insert()
        nelems = preRehashElements;
        collision = preRehashCollisions;
        expand++;

        if (printStats) //if 2nd constructor is used, print info to file
            printStatistics();
        collision = 0;
        return;
    }

    /**
    * Calculate the hash value of a given string using a CRC variant hash
    *
    * @param str the string value
    * @return the hash value (hashValue mod array.length)
    */
    public int hashString(String str)
    {
        int hashValue = 0;

        //for each character in the string, run the CRC hash and XOR with the
        //running total
        for (int i = 0; i < str.length(); i++)
        {
            int ch = (int) str.charAt(i);

            int first5bits = hashValue & 0xf8000000;
            //takes top 5 bits from hashValue
            hashValue = hashValue << 5; //shift top 5 bits leftwise

            hashValue = hashValue ^ (first5bits >>> 27);
            hashValue = hashValue ^ ch;
        }

        return (Math.abs(hashValue) % array.length);
    }

    /**
    * Print statistics to the given file.
    *
    * @return True if successfully printed statistics, false if the file could
    * not be opened/created.
    */
    @Override
    public boolean printStatistics()
    {
        PrintStream out;
        try
        {
            out = new PrintStream(new FileOutputStream(this.statsFileName,
            true));
        } catch (FileNotFoundException e)
        {
            return false;
        }
        out.print(this.expand + " resizes, ");//Print resize times
        //Calculate the load factor
        double loadFactor = ((double) nelems / array.length);
        DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
        out.print("load factor " + df.format(loadFactor) + ", ");
        out.print(this.collision + " collisions, "); //Print collision times
        int length = 0;
        for (int i = 0; i < this.array.length; i++)
        {
            if (this.array[i] != null && this.array[i].size() > length)
            length = this.array[i].size();
        }
        //Print the length of the longest chain
        out.println(length + " longest chain");
        out.close();
        return true;
    }

    /**
    * Generate a prime number that is close to the double of current array size
    *
    * @return a prime number used as array size
    */
    private int primeGen()
    {
        boolean isPrime = false;
        int num = array.length * CONSTANT_TWO;//Double the size

        /*
        * Generate next prime number that is greater than the double of
        * current array size
        */
        while (!isPrime)
        {
            num++;
            /*
            * Try divides the number with all numbers greater than two and
            * less than or equal to the square root of itself
            */
            for (int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
            divisor++)
            {
                if (num % divisor == 0)//The number is divisible
                break;//No need for further testing, break inner loop
                if (divisor == (int) Math.sqrt(num))//The number is indivisible
                isPrime = true;//Then it is a prime
            }
        }
        return num;
    }

}
