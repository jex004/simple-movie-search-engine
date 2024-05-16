import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class BSTreeTest {

    BSTree<Integer> testTree;
    @BeforeEach
    public void setup() {
        testTree = new BSTree<>();
    }

    @Test
    public void BSTreeConstructor() {
        Assertions.assertEquals(-1, testTree.findHeight());
        Assertions.assertEquals(0, testTree.getSize());
        Assertions.assertEquals(null, testTree.getRoot());
    }

    @Test
    public void getRoot() {
        Assertions.assertEquals(null, testTree.getRoot());
        testTree.insert(1);
        Assertions.assertEquals(1, testTree.getRoot().key);
        testTree.insert(2);
        Assertions.assertEquals(1, testTree.getRoot().key);
    }

    @Test
    public void getSize() {
        Assertions.assertEquals(0, testTree.getSize());
        testTree.insert(1);
        Assertions.assertEquals(1, testTree.getSize());
        testTree.insert(2);
        Assertions.assertEquals(2, testTree.getSize());
    }

    @Test
    public void insert() {
        Assertions.assertThrows(NullPointerException.class, ()-> testTree.insert(null));
        Assertions.assertTrue(testTree.insert(1));
        testTree.insert(2);
        Assertions.assertEquals(2, testTree.getSize());
    }

    @Test
    public void findKey() {
        Assertions.assertThrows(NullPointerException.class, ()-> testTree.findKey(null));
        Assertions.assertTrue(testTree.insert(1));
        Assertions.assertTrue(testTree.findKey(1));
        Assertions.assertFalse(testTree.findKey(2));
        Assertions.assertTrue(testTree.insert(2));
        Assertions.assertTrue(testTree.findKey(2));
    }

    @Test
    public void insertData() {
        Assertions.assertThrows(NullPointerException.class, ()-> testTree.insertData(null, 4));
        testTree.insert(1);
        Assertions.assertThrows(NullPointerException.class, ()-> testTree.insertData(1, null));
        testTree.insertData(1, 4);
        LinkedList<Integer> testList = new LinkedList<>();
        testList.add(4);
        Assertions.assertEquals(testList, testTree.findDataList(1));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> testTree.insertData(3, 4));
    }

    @Test
    public void findDataList() {
        Assertions.assertThrows(NullPointerException.class, ()-> testTree.findDataList(null));
        testTree.insert(1);
        testTree.insertData(1, 4);
        LinkedList<Integer> testList = new LinkedList<>();
        testList.add(4);
        Assertions.assertEquals(testList, testTree.findDataList(1));
        testTree.insertData(1, 5);
        testList.add(5);
        Assertions.assertEquals(testList, testTree.findDataList(1));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> testTree.findDataList(5));
    }

    @Test
    public void findHeight() {
        Assertions.assertEquals(-1, testTree.findHeight());
        testTree.insert(0);
        Assertions.assertEquals(0, testTree.findHeight());
        testTree.insert(1);
        testTree.insert(2);
        Assertions.assertEquals(2, testTree.findHeight());
        testTree.insert(2);
        Assertions.assertEquals(2, testTree.findHeight());
    }

    @Test
    public void BSTree_Iterator() {
        BSTree<Integer> myTree = new BSTree<>();
        myTree.insert(1);
        myTree.insert(2);
        myTree.insert(3);
        Assertions.assertNotNull(myTree);
        myTree.insertData(1,1);
        myTree.insertData(2,2);
        myTree.insertData(3,3);
        Assertions.assertNotNull(myTree);
        Iterator<Integer> iter = myTree.iterator();
        Assertions.assertTrue(iter.hasNext());

    }

    @Test
    public void hasNext() {
        Iterator<Integer> iter = testTree.iterator();
        Assertions.assertFalse(iter.hasNext());
        BSTree<Integer> myTree = new BSTree<>();
        Iterator<Integer> iter2 = myTree.iterator();
        myTree.insert(1);
        myTree.insert(2);
        myTree.insert(3);
        Assertions.assertNotNull(myTree);
        Assertions.assertFalse(iter2.hasNext());
        Iterator<Integer> iter3 = myTree.iterator();
        Assertions.assertTrue(iter3.hasNext());
    }

    @Test
    public void next() {
        Iterator<Integer> iter = testTree.iterator();
        Assertions.assertFalse(iter.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, ()-> iter.next());
        testTree.insert(4);
        testTree.insert(1);
        testTree.insert(2);
        testTree.insert(5);
        testTree.insertData(1,10);
        testTree.insertData(2,10);
        testTree.insertData(4,10);
        Iterator<Integer> iter2 = testTree.iterator();
        Assertions.assertTrue(iter2.hasNext());
        Assertions.assertEquals(1, iter2.next());
        Assertions.assertEquals(2, iter2.next());
        Assertions.assertEquals(4, iter2.next());
        Assertions.assertEquals(5, iter2.next());
    }
}