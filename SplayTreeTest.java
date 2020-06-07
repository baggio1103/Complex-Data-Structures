import org.junit.Test;

import static org.junit.Assert.*;

public class SplayTreeTest {

    @Test
    public void find(){
        Node node = new Node(10);
        SplayTree tree = new SplayTree(node);
        int[] values = {8,15,12,17, 13};
        for (int value : values){
            tree.add(value);
        }
        tree.find(15);
        assertEquals(tree.root.value, tree.find(15).value);
        tree.find(13);
        assertEquals(tree.root.value, 13);
        tree.find(12);
        assertEquals(tree.root.value, 12);
    }

    @Test
    public void remove(){
        Node node = new Node(10);
        SplayTree tree = new SplayTree(node);
        int[] values = {8,15,12,17,13};
        for (int value : values){
            tree.add(value);
        }
        tree.remove(8);
        Node root = tree.root;
        assertEquals(13, root.value);
        assertEquals(10, root.leftChild.value);
        assertEquals(17, root.rightChild.value);
        assertEquals(15, root.rightChild.leftChild.value);
        assertEquals(12, root.leftChild.rightChild.value);
        assertNull(root.parent);
        assertNull(root.rightChild.rightChild);
        assertNull(root.leftChild.leftChild);
    }

    @Test
    public void search(){
        Node node = new Node(10);
        SplayTree tree = new SplayTree(node);
        int[] values = {8,15,12,17, 13};
        for (int value : values){
            tree.add(value);
        }
        Node result = tree.search(18);
        assertEquals(17, result.value);
        assertEquals(8, tree.search(9).value);
        assertEquals(13, tree.search(13).value);
        assertEquals(12, tree.search(12).value);
        assertEquals(10, tree.search(10).value);
        assertEquals(15, tree.search(15).value);
        assertEquals(8, tree.search(2).value);
        assertEquals(10, tree.search(11).value);
        assertEquals(15, tree.search(16).value);
    }

    @Test
    public void add() {
        Node node = new Node(10);
//              10
        SplayTree tree = new SplayTree(node);
        Node root;
        int[] values = {8,15,12,17, 13, 19};
        tree.add(8);
//              8
//               \
//                 10
        root = tree.root;
        assertEquals(8, root.value);
        assertNull(root.leftChild);
        assertEquals(10, root.rightChild.value);
        assertEquals(root, root.rightChild.parent);
        assertNull(root.rightChild.rightChild);
        assertNull(root.rightChild.leftChild);
        tree.add(15);
//                15
//               /
//             10
//            /
//           8
        root = tree.root;
        assertEquals(15, root.value);
        assertNull(root.rightChild);
        assertEquals(10, root.leftChild.value);
        assertEquals(8, root.leftChild.leftChild.value);

        tree.add(12);
//                15                    12
//               /                     /  \
//             10       ===>         10    15
//            /   \                 /
//           8     12              8

        root = tree.root;
        assertEquals(12, root.value);
        assertEquals(10, root.leftChild.value);
        assertEquals(8, root.leftChild.leftChild.value);
        assertEquals(15, root.rightChild.value);
        assertNull(root.rightChild.rightChild);

        tree.add(17);
//                    12                            17
//                   /  \                          /
//                 10    15   ===>                15
//                 /       \                     /
//                8         17                  12
//                                             /
//                                            10
//                                           /
//                                          8
        root = tree.root;
        assertEquals(17, root.value);
        assertEquals(15, root.leftChild.value);
        assertEquals(12, root.leftChild.leftChild.value);
        assertEquals(10, root.leftChild.leftChild.leftChild.value);
        assertNull(root.rightChild);


        tree.add(13);
//                        17                            13
//                       /                             /  \
//                     15                            12     17
//                    /                             /      /
//                  12          ===>              10      15
//                 /  \                          /
//               10    13                       8
//              /
//             8

        root = tree.root;
        assertNull(root.parent);
        assertEquals(13, root.value);
        assertEquals(12, root.leftChild.value);
        assertEquals(10, root.leftChild.leftChild.value);
        assertEquals(8, root.leftChild.leftChild.leftChild.value);
        assertEquals(17, root.rightChild.value);
        assertEquals(15, root.rightChild.leftChild.value);
        assertNull(root.rightChild.rightChild);
    }
}
