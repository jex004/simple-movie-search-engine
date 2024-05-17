/*
 * Name: Jenny Xu
 * PID:  A17844311
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Jenny Xu
 * @since  A17844311
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    /**
     * Instantiates BSTNode
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this(left, right, new LinkedList<>(), key);
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (findKey(key)) {
            return false;
        }
        root = insertHelper(root, key);
        nelems++;
        return true;
    }

    /**
     * Helper function for insert
     * @param node The node assigned to the key
     * @param key The key being inserted
     * @return Returns the node
     */
    private BSTNode insertHelper(BSTNode node, T key) {
        if (node == null) {
            return new BSTNode(null, null, key);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = (insertHelper(node.left, key));
        } else {
            node.right = insertHelper(node.getRight(), key);
        }
        return node;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return findKeyHelper(root, key);
    }

    /**
     * Helper function of findKey
     * @param node Node to check
     * @param key Target key
     * @return True if key is found, false otherwise
     */
    private boolean findKeyHelper(BSTNode node, T key) {
        if (node == null) {
            return false;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return findKeyHelper(node.left, key);
        }
        else if (compare > 0){
            return findKeyHelper(node.right, key);
        } else {
            return true;
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        insertDataHelper(root, key, data);
    }

    /**
     * Helper function for insertData
     * @param node Node to insert the data into
     * @param key Key at which the data should be inserted
     * @param data Data that should be inserted
     */
    private void insertDataHelper(BSTNode node, T key, T data) {
        if (node == null) {
            return;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            insertDataHelper(node.left, key, data);
        }
        else if (compare > 0) {
            insertDataHelper(node.right, key, data);
        } else {
            node.addNewInfo(data);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList<T> dataList = findDataListHelper(root, key);
        if (dataList == null) {
            throw new IllegalArgumentException();
        }
        return dataList;
    }

    /**
     * Helper function for findDataList
     * @param node A node to check
     * @param key Target key
     * @return Returns the data list
     */
    private LinkedList<T> findDataListHelper(BSTNode node, T key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return findDataListHelper(node.left, key);
        }
        else if (compare > 0) {
            return findDataListHelper(node.right, key);
        } else {
            return node.dataList;
        }
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = findHeightHelper(root.left);
        int rightHeight = findHeightHelper(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /* * * * * BST Iterator * * * * */

    /**
     * Instantiates the iterator
     */
    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> stack;
        public BSTree_Iterator() {
            stack = new Stack<>();
            BSTNode node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Checks if the stack is empty
         * @return Returns true if the stack is empty; Returns false otherwise
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Pops the next node in the stack
         * @return Returns the next node in the stack
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode current = stack.pop();
            BSTNode node = current.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            return current.key;
        }
    }

    /**
     * Method to create a new iterator
     * @return Returns a new iterator
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
