/**
 * Binary Search Tree with a generic comparable Key used for searching and a
 * value to hold. The tree is not necessarily balanced and allows for searching,
 * removing, inserting and in order traversal. Uses a BST Node to store data
 * and keep track of references to other nodes.
 * Iterating through the tree in order allows for a sorted list.
 *
 * NAME: Eric Jin
 * PID: A15816483
 * ID: cs12sp20aum
 * EMAIL: erjin@ucsd.edu
 */
import java.util.ArrayList;

/**
 * Binary Search tree with a key used for searching and a value for it to hold.
 * Methods for search, insert, delete and returning a sorted list.
 */
public class MyBST<K extends Comparable<K>, V> {

    /**
     * Nested Node class that has generic K and V values, as well as pointers
     * to the parent and left and right child nodes.
     */
    static class MyBSTNode<K, V> {

        K key;
        V value;
        MyBSTNode<K, V> parent;
        MyBSTNode<K, V> left;
        MyBSTNode<K, V> right;

        /**
         * Constructor that initializes a node
         * @param key - key of the new node
         * @param value - value of the new node
         * @param parent - reference to the parent
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent)
        {
            this.key = key;
            this.value = value;
            this.parent = parent;
            left = null;
            right = null;
        }

        /**
         * Getter for the key of the node
         * @return key
         */
        public K getKey()
        {
            return key;
        }

        /**
         * Getter for the value of the node
         * @return value
         */
        public V getValue()
        {
            return value;
        }

        /**
         * Getter for the parent of the node
         * @return reference to the parent node
         */
        public MyBSTNode<K, V> getParent()
        {
            return parent;
        }

        /**
         * Getter for the left node reference
         * @return reference to the left node
         */
        public MyBSTNode<K, V> getLeft()
        {
            return left;
        }

        /**
         * Getter for the right node reference
         * @return reference to the right node
         */
        public MyBSTNode<K, V> getRight()
        {
            return right;
        }

        /**
         * Setter for the key
         * @param newKey - new value to set the key
         */
        public void setKey(K newKey)
        {
            key = newKey;
        }

        /**
         * Setter for the values
         * @param newValue - new value to set the value
         */
        public void setValue(V newValue)
        {
            value = newValue;
        }

        /**
         * Setter for parent reference
         * @param newParent - node to become the new parent
         */
        public void setParent(MyBSTNode<K, V> newParent)
        {
            parent = newParent;
        }

        /**
         * Setter for left node reference
         * @param newLeft - node to become the new left node
         */
        public void setLeft(MyBSTNode<K, V> newLeft)
        {
            left = newLeft;
        }

        /**
         * Setter for right node reference
         * @param newRight - node to become the new right node
         */
        public void setRight(MyBSTNode<K, V> newRight)
        {
            right = newRight;
        }

        /**
         * Returns the successor to this node. The successor is the node that
         * has the next largest key value in the tree. Returns null if there is
         * no larger key value.
         * @return successor node
         */
        public MyBSTNode<K, V> successor()
        {
            MyBSTNode<K, V> successorNode, largestNode;
            if(right != null)
            {
                successorNode = right;
                while(successorNode.left != null)
                    successorNode = successorNode.left;
            }
            else
            {
                largestNode = parent;
                while(parent != null)
                    largestNode = largestNode.parent;
                while(right != null)
                    largestNode = largestNode.right;
                if(largestNode == this)
                    return null;

                successorNode = this.parent;
                while(successorNode.parent != null &&
                successorNode == successorNode.parent.right)
                {
                    successorNode = successorNode.parent;
                }
            }
            return successorNode;
        }
    }


    ///////////BST METHODS START HERE/////////////

    MyBSTNode<K, V> root;
    int size;

    /**
     * Creates an empty binary tree
     */
    public MyBST()
    {
        root = null;
        size = 0;
    }

    public void covid19(MyBSTNode<K, V> node)
    {
        if(node == null)
            return;
        else
        {
            System.out.println(node.getValue());
            System.out.println(node.getValue());
            covid19(node.getLeft());
            covid19(node.getLeft());
            covid19(node.getRight());
        }
    }

    /**
     * Returns the node in the tree that has the next highest key value. If the
     * current node has the largest key in the tree, return null.
     * @param node finds the successor to this node
     * @return the successor to curent node, or null if there is no succeesor
     *
     */
    protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node)
    {
        MyBSTNode<K, V> successorNode;
        if(node == null)
            return null;

        //if there is a node to this nodes right, the next largest node is
        //the right child, then go all the way left.
        if(node.getRight() != null)
        {
            successorNode = node.getRight();
            while(successorNode.getLeft() != null)
            {
                successorNode = successorNode.getLeft();
            }
            return successorNode;
        }
        else
        {
            MyBSTNode<K, V> largestNode = root;
            while(largestNode.getRight() != null)
            {
                largestNode = largestNode.getRight();
            }
            if(largestNode == node)
                return null;

            MyBSTNode<K, V> parentN = node.getParent();
            while(parentN != null && node == parentN.getRight())
            {
                node = parentN;
                parentN = parentN.getParent();
            }
            return parentN;
        }
    }

    /**
     * Returns the number of nodes in the tree
     * @return number of nodes in the tree
     */
    public int size()
    {
        return size;
    }


    /**
     * Inserts a node with key and value into the tree. If there is already a
     * node with the same key, replace such node with the new value and return
     * the old value, else return null.
     * @param key - node to be inserted with the key
     * @param value - node to be inserted with the value
     * @return - the old value if there was already a node with the key,
     * null otherwise.
     */
    public V insert(K key, V value)
    {
        if(key == null)
            throw new NullPointerException();

        MyBSTNode<K, V> toInsert = new MyBSTNode<>(key, value, null);

        if(root == null)
        {
            root = toInsert;
            size++;
            return null;
        }
        else
        {
            MyBSTNode<K, V> compareTarget = root;

            while(true)
            {
                if(compareTarget.getKey().compareTo(toInsert.getKey()) > 0)
                {
                    if(compareTarget.getLeft() == null) //reached a leaf
                    {
                        compareTarget.setLeft(toInsert);
                        toInsert.setParent(compareTarget);
                        size++;
                        return null;
                    }
                    compareTarget = compareTarget.getLeft();
                }
                else if(compareTarget.getKey().compareTo(toInsert.getKey()) < 0)
                {
                    if(compareTarget.getRight() == null) //reached a leaf
                    {
                        compareTarget.setRight(toInsert);
                        toInsert.setParent(compareTarget);
                        size++;
                        return null;
                    }
                    compareTarget = compareTarget.getRight();
                }
                else //we have found a node with the same key
                {
                    V removedValue = compareTarget.getValue();
                    compareTarget.setValue(value);
                    size++;
                    return removedValue;
                }
            }
        }
    }


    /**
     * Given a key, return the value linked to that key in the tree, or null
     * if the value does not exist. Note null is a valid value.
     * @param key - key to search the BST with
     * @return the value associated with the key, or null if it cannot be found
     *
     */
    public V search(K key)
    {
        if(key == null || root == null)
            return null;
        else
        {
            MyBSTNode<K, V> compareTarget = root;
            while(compareTarget != null)
            {
                if(compareTarget.getKey().compareTo(key) > 0)
                    compareTarget = compareTarget.getLeft();
                else if(compareTarget.getKey().compareTo(key) < 0)
                    compareTarget = compareTarget.getRight();
                else
                    return compareTarget.getValue(); //key found
            }
            return null; //key not found
        }
    }

    /**
     * Given a key, search and remove the node that has said key and return the
     * node's value. If no key exists, return null. If a node is a leaf, it is
     * simply deleted. If it has one child, the node is replaced by its child.
     * If the node has two children, the node is replaced by its successor.
     * @param key - finds the node with this key
     * @return the value of the node that was removed.
     */
    public V remove(K key)
    {
        if(root == null) //empty tree
            return null;

        //if the node to remove is a root
        else if(root.getKey().compareTo(key) == 0)
        {
            V rootVal = root.getValue();
            if(size == 1) //root is the only node
            {
                size = 0;
                root = null;
                return rootVal;
            }
            else if(root.getRight() == null)
            {
                root = root.getLeft();
                root.getParent().setLeft(null);//removes old root from the tree
                root.setParent(null);
                size--;
                return rootVal;

            }
            else if(root.getLeft() == null)
            {
                root = root.getRight();
                root.getParent().setRight(null);//removes old root from the tree
                root.setParent(null);
                size--;
                return rootVal;
            }
            else //root has 2 children
            {
                MyBSTNode<K ,V> replacement = successor(root);
                remove(replacement.getKey()); //remove the successor from tree

                replacement.setLeft(root.getLeft());
                replacement.setRight(root.getRight());
                try{
                    root.getLeft().setParent(replacement);
                } catch (NullPointerException e) {
                    //do nothing
                }

                try{
                    root.getRight().setParent(replacement);
                } catch (NullPointerException e) {
                    //do nothing
                }

                root.setLeft(null);
                root.setRight(null);
                root = replacement;
                return rootVal;

            }
        }
        else //if the node to remove is not the root
        {
            MyBSTNode<K ,V> toRemove = root;
            V removedValue;

            while(toRemove != null)
            {
                if(toRemove.getKey().compareTo(key) > 0)
                    toRemove = toRemove.getLeft();
                else if(toRemove.getKey().compareTo(key) < 0)
                    toRemove = toRemove.getRight();
                else //key found
                {
                    removedValue = toRemove.getValue();

                    if(toRemove.getRight() == null &&toRemove.getLeft() == null)
                    { //leaf node
                        if(toRemove.getParent().getLeft() == toRemove)
                            toRemove.getParent().setLeft(null);
                        else
                            toRemove.getParent().setRight(null);
                        size--;
                    }
                    else if(toRemove.getRight() != null
                        && toRemove.getLeft() == null)
                    { //there is a right child
                        if(toRemove.getParent().getLeft() == toRemove)
                            toRemove.getParent().setLeft(toRemove.getRight());
                        else
                            toRemove.getParent().setRight(toRemove.getRight());
                        size--;
                    }
                    else if(toRemove.getRight() == null
                        && toRemove.getLeft() != null)
                    { //there is a left child
                        if(toRemove.getParent().getLeft() == toRemove)
                            toRemove.getParent().setLeft(toRemove.getLeft());
                        else
                            toRemove.getParent().setRight(toRemove.getLeft());
                        size--;
                    }
                    else //there are two children
                    {
                        MyBSTNode<K ,V> replacement = successor(toRemove);
                        remove(replacement.getKey()); //remove successor

                        replacement.setLeft(toRemove.getLeft());
                        replacement.setRight(toRemove.getRight());
                        replacement.setParent(toRemove.getParent());
                    }
                    //remove removed node's access to the tree
                    toRemove.setLeft(null);
                    toRemove.setRight(null);
                    toRemove.setParent(null);
                    return removedValue;
                }
            }
            return null; //node not found
        }

    }

    /**
     * Traverses through the tree inorder and returns a sorted list of all the
     * nodes. If the tree is empty, return an empty arrayList.
     * @return arraylist of sorted notes
     *
     */
    public ArrayList<MyBSTNode<K, V>> inorder()
    {
        ArrayList<MyBSTNode<K, V>> sorted = new ArrayList<>();
        if(root == null)
            return sorted;

        MyBSTNode<K, V> firstNode = root;
        //first value is the left most node in the tree
        while(firstNode.getLeft() != null)
        {
            firstNode = firstNode.getLeft();
        }

        MyBSTNode<K, V> nextInLine = firstNode;
        while(nextInLine != null)
        {
            sorted.add(nextInLine);
            nextInLine = successor(nextInLine);
        }
        return sorted;
    }
}
