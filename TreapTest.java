package Treap;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreapTest {

    @Test
    public void construct() {
        int[] xs = {0,2,3,4,5,6,7,9,11,13,14};
        int[] ys = {3,4,3,6,1,2,10,7,3,8,4};
        Treap treap = new Treap(xs, ys);
//        treap.traverse(treap.root);
        Node root = treap.root;

        assertEquals(7, root.value);
        assertEquals(10, root.priority);

        assertEquals(4, root.leftChild.value);
        assertEquals(6, root.leftChild.priority);

        assertEquals(13, root.rightChild.value);
        assertEquals(8, root.rightChild.priority);

        assertEquals(6, root.leftChild.rightChild.value);
        assertEquals(2, root.leftChild.rightChild.priority);

        assertEquals(14, root.rightChild.rightChild.value);
        assertEquals(4,root.rightChild.rightChild.priority);

        assertNull(root.rightChild.rightChild.rightChild);


        

    }
}