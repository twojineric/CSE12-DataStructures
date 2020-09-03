/**
 * Generic Binary Tree that works with objects that extend Comparable. The tree
 * uses a Node class that has two references to other Node objects. The tree
 * is complete and uses BFS via a Queue ADT to perform most of the methods.
 *
 * NAME: Eric Jin
 * ID: cs12sp20aum
 * PID: A15816483
 * EMAIL: erjin@ucsd.edu
 *
 * SOURCES: None
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Generic Binary Tree class that uses a Node class and a LinkedList to traverse
 * the tree. The tree has no null objects.
 */
public class BinaryTree<E extends Comparable<E>> {

    /**
     * Node that holds data and has references to a left Node and a right Node
     * Set and get left and right nodes
     */
    protected class Node
    {
        Node left;
        Node right;
        E data;

        /**
         * Constructor creates a node with data.
         */
        public Node(E data)
        {
            this.data = data;
            left = null;
            right = null;
        }

        /**
         * Sets the left node to a specific Node
         * @param left - Node to become the left node
         */
        public void setLeft(Node left)
        {
            this.left = left;
        }

        /**
         * Sets the right node to a specific Node
         * @param right - Node to become the right node
         */
        public void setRight(Node right)
        {
            this.right = right;
        }

        /**
         * Sets the data of this node
         * @param data - new data value
         */
        public void setData(E data)
        {
            this.data = data;
        }

        /**
         * Returns the left Node
         * @return left Node
         */
        public Node getLeft()
        {
            return left;
        }

        /**
         * Returns the right node
         * @return right Node
         */
        public Node getRight()
        {
            return right;
        }

        /**
         * Returns the data of the node
         * @return E data of the node
         */
        public E getData()
        {
            return data;
        }
    }

//////// BEGIN BINARY TREE METHODS //////////

    Node root;
    int size; //amount of nodes in the tree
    private static final int BASE_TWO = 2;

    /**
     * Default, no args constructor
     */
    public BinaryTree()
    {
        root = null;
    }

    /**
     * Creates a binary tree with a single node.
     */
    public BinaryTree(E data)
    {
        root = new Node(data);
        size++;
    }

    /**
     * Creates a binary tree from a list. Will insert the items in order (BFS)
     * Assumes that no items in list are null or list is not null
     * @param list - list to turn into binary tree
     */
    public BinaryTree(List<E> list)
    {
        for(E item : list)
        {
            this.add(item);
        }
    }

    /**
     * Inserts the specified element at the end of the binary tree.
     * @throws NullPointerException if element is null
     * @param element - element to be inserted
     */
    public void add(E element)
    {
        if(element == null)
            throw new NullPointerException();
        else if(root == null)
        {
            Node newRoot = new Node(element);
            root = newRoot;
            size = 1;
        }
        else
        {
            Node insertedNode = new Node(element);
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            Node nextNode = root;

            //loop traverses through the tree (BFS) until the end is reached
            while(true)
            {
                nextNode = queue.poll(); //uses a queue to traverse

                //insert element at the first empty (null) point in the tree
                //and stop traversing
                if(nextNode.getLeft() == null)
                {
                    nextNode.setLeft(insertedNode);
                    break;
                }
                else if(nextNode.getRight() == null)
                {
                    nextNode.setRight(insertedNode);
                    break;
                }
                else
                {
                    queue.add(nextNode.getLeft());
                    queue.add(nextNode.getRight());
                }
            }
            size++;
        }
    }

    /**
     * Removes the specified element and replaces it with the element
     * furthest right on the lowest level. If there are multiple of the same
     * element, remove the first one (when using BFS).
     * @throws NullPointerException if element is null
     * @param element - element to be removed
     * @return true if the element was successfully removed, false otherwise
     */
    public boolean remove(E element)
    {
        if(element == null)
            throw new NullPointerException();

        if(!containsBFS(element)) //we cant remove an element that doesnt exist
            return false;
        else //the element exists in the tree
        {
            if(this.getSize() == 1) //edge case: if root is the only element
            {
                root = null;
                size--;
                return true;
            }

            //We traverse the tree twice. First traversal is to find and remove
            //the last element (bottom and rightmost node) and save into
            //the variable replacement
            Queue<Node> firstSearch = new LinkedList<Node>();
            firstSearch.add(root);
            Node prevRemovedNode = root;
            Node nextNode = root;
            Node replacement; //this is the last element of the tree

            while(true)
            {
                prevRemovedNode = nextNode;
                nextNode = firstSearch.poll();

                if(nextNode.getLeft() != null && nextNode.getRight() == null)
                {
                    //if right child is the first null item, it means the
                    //last (non null) node is the left child
                    //of the current popped node
                    replacement = nextNode.getLeft();
                    nextNode.setLeft(null);
                    break;
                }
                else if(nextNode.getLeft() == null)
                {
                    //if the left child is the first null item, it means the
                    //last node is the right child of the node that was
                    //popped before the current one
                    replacement = prevRemovedNode.getRight();
                    prevRemovedNode.setRight(null);
                    break;
                }
                else
                {
                    //continue until the first null child node is reached
                    firstSearch.add(nextNode.getLeft());
                    firstSearch.add(nextNode.getRight());
                }
            }

            //edge case: if the last element is also the one to be removed
            if(replacement.getData().equals(element))
            {
                size--;
                return true;
            }

            //The second traversal finds the element that needs to be removed,
            //replaces it with the last node in the tree (found above) and
            //reassigns references to fix the tree.
            boolean firstInstance = true;
            Queue<Node> secondSearch = new LinkedList<Node>();
            secondSearch.add(root);
            Node removeTarget = null;

            //edge case: if the root is the element to be removed
            if(root.getData().equals(element))
            {
                replacement.setLeft(root.getLeft());
                replacement.setRight(root.getRight());
                root.setLeft(null); //remove prev root's pointers
                root.setRight(null);

                root = replacement;
                size--;
                return true;
            }

            while(true) //continue traversing the tree until element is found
            {
                Node laterNode = secondSearch.poll();

                if(laterNode.getLeft().getData().equals(element))
                {
                    //the removeTarget element is the child of the current node
                    removeTarget = laterNode.getLeft();
                    Node leftChild = removeTarget.getLeft();
                    Node rightChild = removeTarget.getRight();
                    //remove remmovedNode's access to the tree
                    removeTarget.setLeft(null);
                    removeTarget.setRight(null);
                    //reassign references
                    laterNode.setLeft(replacement);
                    replacement.setLeft(leftChild);
                    replacement.setRight(rightChild);
                    break;
                }

                if(laterNode.getRight().getData().equals(element))
                {
                    //the element is the child of the current node
                    removeTarget = laterNode.getRight();
                    Node leftChild = removeTarget.getLeft();
                    Node rightChild = removeTarget.getRight();
                    //remove remmovedNode's access to the tree
                    removeTarget.setLeft(null);
                    removeTarget.setRight(null);
                    //reassign references to fix the tree
                    laterNode.setRight(replacement);
                    replacement.setLeft(leftChild);
                    replacement.setRight(rightChild);
                    break;
                }

                secondSearch.add(laterNode.getLeft());
                secondSearch.add(laterNode.getRight());
            }
            size--;
            return true;
        }
    }

    /**
     * Searches the tree (breadth first search) for the specified element
     * @throws NullPointerException if element is null
     * @param element - element to search for
     * @return true if the element is in the tree, false otherwise
     */
    public boolean containsBFS(E element)
    {
        if(element == null)
            throw new NullPointerException();

        if(root == null)
            return false;
        else
        {
            Queue<Node> queue = new LinkedList<Node>(); //use a queue for BFS
            queue.add(root);

            while(!queue.isEmpty())
            {
                Node nextNode = queue.poll();

                if(nextNode.getData().equals(element))
                    return true;

                if(nextNode.getLeft() != null)
                    queue.add(nextNode.getLeft());
                if(nextNode.getRight() != null)
                    queue.add(nextNode.getRight());
            }
            return false;
        }
    }

    /**
     * Traverses the tree and returns the smallest element in the tree, as
     * determined by the compareTo() method.
     * @return E - smallest element in the binary tree
     */
    public E minValue()
    {
        if(size == 0 || root == null)
            return null;
        else
        {
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            Node smallestNode = root;

            while(!queue.isEmpty())
            {
                Node nextNode = queue.poll();

                if(nextNode.getData().compareTo(smallestNode.getData()) < 0)
                    smallestNode = nextNode;

                if(nextNode.getLeft() != null)
                    queue.add(nextNode.getLeft());
                if(nextNode.getRight() != null)
                    queue.add(nextNode.getRight());
            }

            return smallestNode.getData();
        }
    }

    /**
     * Returns the number of nodes in the tree
     * @return number of nodes in the tree
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Returns the height of the tree. An empty tree or a tree with only 1 node
     * both have a height of 0.
     * @return tree height
     */
    public int getHeight()
    {
        if(root == null)
            return 0;
        else if(this.getSize() == 1)
            return 0;
        else
            return (int)Math.floor(Math.log(this.getSize())/Math.log(BASE_TWO));
        //Math.log function is base 10 only, to calculate log2(x) we do
        //log(x) / log(2)
    }

}
