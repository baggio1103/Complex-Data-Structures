package Treap;
import java.util.Arrays;

public class Treap {
    Node root;
    int[] values;
    int[] priorities;

    public Treap(int[] xCoordinates, int[] yCoordinates){
        this.values = xCoordinates;
        this.priorities = yCoordinates;
        Arrays.sort(xCoordinates);
        int maxElemIndex = getMax(yCoordinates, 0, yCoordinates.length-1);
        root = new Node(xCoordinates[maxElemIndex], yCoordinates[maxElemIndex]);
        root.leftChild = construct(0, maxElemIndex-1);
        root.rightChild = construct(maxElemIndex+1, yCoordinates.length-1);
    }

    public Treap(Node node){
        this.root = node;
    }

    public Node construct(int left, int right){
        if (left > right){
            return null;
        }
        int index = getMax(priorities, left, right);
        Node node = new Node(values[index], priorities[index]);
        node.leftChild = construct(left, index-1);
        node.rightChild = construct(index+1, right);
        return node;
    }

    public static Treap merge(Treap leftTree, Treap rightTree){
        if (leftTree.root == null){
            return rightTree;
        }
        if (rightTree.root == null){
            return leftTree;
        }

        if (leftTree.root.priority > rightTree.root.priority){
            Treap treap = merge(new Treap(leftTree.root.rightChild), rightTree);
            leftTree.root.rightChild = treap.root;
            return leftTree;
        }else {
            Treap treap = merge(leftTree, new Treap(rightTree.root.leftChild));
            rightTree.root.leftChild = treap.root;
            return rightTree;
        }
    }

    public int getMax(int[] array, int left, int right){
        int maxElementIndex = left;
        for (int i = left+1; i <= right; i++){
            if (array[i] > array[maxElementIndex]){
                maxElementIndex = i;
            }
        }
        return maxElementIndex;
    }

    public void traverse(Node node){
        if (node == null){
            return;
        }
        System.out.println("{ "+node.value + ", " + node.priority + "} ");
        traverse(node.leftChild);
        traverse(node.rightChild);
    }

    public static void main(String[] args) {
        int[] xs = {0,2,3,4,5,6,7,9,11,13,14};
        int[] ys = {3,4,3,6,1,2,10,7,3,8,4};
        Treap treap = new Treap(xs, ys);
        treap.traverse(treap.root);
    }

}

class Node{
    int value;
    int priority;
    Node leftChild;
    Node rightChild;
    public Node(int value, int priority){
        this.value = value;
        this.priority = priority;
        leftChild = rightChild = null;
    }
}
