import java.util.ArrayList;

public class DisjointSetUnion {
    
    private ArrayList<Node> list = new ArrayList<>();

    public DisjointSetUnion(){
    }

    public void MakeSet(Node node){
        // here we create a new tree all the time
        list.add(node);
    }

    public Node Find(Node node){
        //here we search for the root of the tree to which the node belongs
        while (node != null){
            if (node.parent == null){
                return node;
            }
            node = node.parent;
        }
        return null;
    }

    public void Union(Node leftNode, Node rightNode){
        /*
         before uniting two trees, it is necessary to check whether they belong to different trees
         if they do, we unite them, otherwise we leave them as they are.
        */

        Node left = Find(leftNode);
        // left gets the root of its tree
        Node right = Find(rightNode);
        // right gets the root of its tree

        if (right != null && left != null){
            if (left.value != right.value){
                // if true, they belong to different trees and we unite them
                Node temp = leftNode;
                while (temp.value != left.value){
                    temp = temp.parent;
                    leftNode.parent = left;
                    leftNode = temp;
                }
            }
            leftNode.parent = right;
            list.remove(leftNode);
        }
    }

    public static void main(String[] args) {
        DisjointSetUnion dsu = new DisjointSetUnion();
        Node node = new Node(10, null);
        Node node1 = new Node(15, null);
        Node node2 = new Node(20, null);
        Node node3 = new Node(25, null);
        dsu.MakeSet(node);
        dsu.MakeSet(node1);
        dsu.MakeSet(node2);
        dsu.MakeSet(node3);
        dsu.Union(node, node1);
        dsu.Union(node2, node3);
        dsu.Union(node, node2);
        System.out.println(dsu.list.size());
        System.out.println(1 == dsu.list.size());
    }

}

class Node {
    public int value;
    public Node parent;

    public Node(int value, Node parent){
        this.value = value;
        this.parent = parent;
    }
}
