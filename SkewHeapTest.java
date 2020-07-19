import org.junit.Test;
import static org.junit.Assert.*;

public class SkewHeapTest {

    @Test
    public void merge() {
        Node node = new Node(5);           //                             5                                                                            3
        node.leftChild = new Node(10);     //                           /   \                                                                       /      \
        node.rightChild = new Node(12);    //                         10     12                                                                    5        7

        Node node1 = new Node(3);           //                      3                    After merge(node, node1)                                /   \     /
        node1.leftChild = new Node(7);      //                    /    \                                                                        8    10 | 14
        node1.rightChild = new Node(8);     //                  7        8                                                                     /
        node1.leftChild.leftChild = new Node(14); //          /                                                                               12
                                                        //        14
        SkewHeap skewHeap = new SkewHeap(node);
        node = skewHeap.merge(node, node1);
        assertEquals(3, node.value);
        assertEquals(5, node.leftChild.value);
        assertEquals(7, node.rightChild.value);

        assertNull(node.rightChild.rightChild);
        assertEquals(14, node.rightChild.leftChild.value);

        assertEquals(10, node.leftChild.rightChild.value);
        assertNull(node.leftChild.rightChild.rightChild);
        assertNull(node.leftChild.rightChild.leftChild);

        assertEquals(8, node.leftChild.leftChild.value);
        assertNull(node.leftChild.leftChild.rightChild);
        assertEquals(12, node.leftChild.leftChild.leftChild.value);
        assertNull(node.leftChild.leftChild.leftChild.leftChild);
    }

    @Test
    public void add() {
        SkewHeap heap = new SkewHeap(new Node(3));  //     3   add(5) = >         3    add(6) = >         3         add(1)  = >                 1
        heap.add(new Node(5));                     //                            /                      /    \                                /
        Node root = heap.root;                          //                            5                      6      5                              3
        assertEquals(3, root.value);           //                                                                                       /   \
        assertEquals(5, root.leftChild.value); //                                                                                      5     6
        assertNull(root.rightChild);

        heap.add(new Node(6));
        assertEquals(3, heap.root.value);
        assertEquals(6, heap.root.leftChild.value);
        assertEquals(5, heap.root.rightChild.value);

        heap.add(new Node(1));
        assertNull(heap.root.rightChild);
        assertEquals(3, heap.root.leftChild.value);
        assertEquals(5, heap.root.leftChild.leftChild.value);
        assertEquals(6, heap.root.leftChild.rightChild.value);
    }

    @Test
    public void remove() {
        SkewHeap heap = new SkewHeap(new Node(3));
        heap.add(new Node(5));     //           1                                                  3
        heap.add(new Node(6));     //         /   \                 remove();                    /   \
        heap.add(new Node(1));     //        7      5                                          6      5
        heap.add(new Node(7));     //             /   \                                       /
                                         //            5      6                                    7

        Node removed = heap.remove(); //removing the root and then rebuilding the tree
        assertEquals(1, removed.value);

        assertEquals(3, heap.root.value);
        assertEquals(6, heap.root.leftChild.value);
        assertEquals(7, heap.root.leftChild.leftChild.value);
        assertNull(heap.root.leftChild.leftChild.leftChild);
        assertNull(heap.root.leftChild.leftChild.rightChild);

        assertEquals(5, heap.root.rightChild.value);
        assertNull(heap.root.rightChild.leftChild);
        assertNull(heap.root.rightChild.rightChild);
    }

}