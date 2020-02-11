import java.util.ArrayList;

public class LCA {

    public Node root;

    public LCA(int[] array){
        int value = getMin(array, 0, array.length-1);
        root = new Node(value);
        root.leftBorder = 0;
        root.rightBorder = array.length-1;
        recursion(array, root, 0, array.length-1);
    }

    public void recursion(int[] array, Node node, int left, int right){
        if (left == right){
            return;
        }
        int middle = (left+right)/2;
        node.leftChild = new Node(getMin(array, left, middle));
        node.leftChild.leftBorder = left;
        node.leftChild.rightBorder = middle;
        node.rightChild = new Node(getMin(array, middle+1,right));
        node.rightChild.rightBorder = right;
        node.rightChild.leftBorder = middle+1;
        recursion(array, node.leftChild, left, middle);
        recursion(array, node.rightChild, middle+1, right);
    }

    public int getMin(int[] temp ,int left, int right){
        int min = temp[left];
        for (int i = left+1; i <= right; i++){
            if (min > temp[i]){
                min = temp[i];
            }
        }
        return min;
    }

    public void traverse(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.value + " ");
        traverse(node.leftChild);
        traverse(node.rightChild);
    }

    public int query(int i, int j){
        ArrayList<Node> list = new ArrayList<Node>();
        recursion(root, list,i,j);
        return list.remove(0).value;
    }

    public void recursion(Node node, ArrayList<Node> list, int i, int j){
        if (node.leftBorder == i && node.rightBorder == j){
            list.add(node);
            return;
        }
        // checking whether i and j are in different childNodes
        // if true - it would mean that i in the leftChild and j in the rightChild
        if (defBorders(node.leftChild, i, j)){
            Node l = getNode(node.leftChild, i, false);
            Node r = getNode(node.rightChild, j, true);
            if (l.value < r.value){
                list.add(l);
                return;
            }else {
                list.add(r);
                return;
            }
        }
        if (node.leftChild.rightBorder >= j){
            node = node.leftChild;
            recursion(node,list, i, j);
        }else if (node.rightChild.leftBorder >= i){
            node = node.rightChild;
            recursion(node,list,i,j);
        }
    }

    public boolean defBorders(Node node, int i, int j){
        return node.rightBorder >= i && node.rightBorder < j;
    }

    public Node getNode(Node node, int i, boolean isRight){
        if (isRight) {
            while (node.rightBorder != i) {
                if (node.leftChild.rightBorder >= i){
                    node = node.leftChild;
                }else {
                    node = node.rightChild;
                }
            }
        }else {
            while (node.leftBorder != i){
                if (node.leftChild.rightBorder >= i){
                    node = node.leftChild;
                }else{
                    node = node.rightChild;
                }
            }
        }
        return node;
    }
}

class Node{
    public int value;
    public Node leftChild;
    public Node rightChild;
    public int leftBorder;
    public int rightBorder;

    public Node(int value){
        this.value = value;
        leftChild = rightChild = null;
    }
}
