import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.LinkedList;

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
}