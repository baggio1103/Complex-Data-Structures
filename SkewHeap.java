public class SkewHeap {
    Node root;
    public SkewHeap(Node node){
        this.root = node;
    }

    public SkewHeap merge(SkewHeap heap1, SkewHeap heap2){
        if (heap1 == null){
            return heap2;
        }else if (heap2 == null){
            return heap1;
        }else {
            heap1.root = merge(heap1.root, heap2.root);
            return heap1;
        }
    }

    public Node merge(Node node1, Node node2){
        if (node1 == null && node2 == null){
            return null;
        }

        if (node1 == null){
            swapSubtrees(node2);
            return node2;
        }

        if (node2 == null){
            swapSubtrees(node1);
            return node1;
        }

        if (node1.value > node2.value){
            Node temp = node1;
            node1 = node2;
            node2 = temp;
        }

        swapSubtrees(node1);
        node1.leftChild = merge(node1.leftChild, node2);
        return node1;

    }

    public void add(Node node){
        root = merge(root, node);
    }

    public Node remove(){
        Node leftSubtree = root.leftChild;
        Node rightSubtree = root.rightChild;
        Node removedNode = root;
        root = merge(leftSubtree, rightSubtree);
        return removedNode;
    }

    public void swapSubtrees(Node node){
        if (node.rightChild != null && node.leftChild != null){
            Node temp = node.rightChild;
            node.rightChild = node.leftChild;
            node.leftChild = temp;
        }

        if (node.leftChild != null || node.rightChild != null){
            if (node.leftChild == null) {
                node.leftChild = node.rightChild;
                node.rightChild = null;
            }

            if (node.rightChild == null) {
                node.rightChild = node.leftChild;
                node.leftChild = null;
            }
        }
    }

}

class Node{
    Node leftChild;
    Node rightChild;
    int value;
    public Node(int value){
        this.value = value;
        leftChild = rightChild = null;
    }
}
