package IncrementalConnectivity;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetUnion {

    public List<Node> forest;
    public List<Node> nodeList;

    public DisjointSetUnion() {
        forest = new ArrayList<>();
        nodeList = new ArrayList<>();
    }

    public void makeSet(Node node) {
        // here we add a new tree to our list of trees, or forest.
        forest.add(node);
        nodeList.add(node);
    }

    public Node find(Node nod) {
        //here we search for the root of the tree to which the node belongs
        Node node = getNode(nod);
        while (node != null) {
            if (node.parent == null) {
                return node;
            }
            node = node.parent;
        }
        return null;
    }

    public void unite(Node leftNode, Node rightNode) {
        /*** leftNode and rightNode stand for trees
         before uniting two trees, it is necessary to check whether they belong to different trees
         if they do, we unite them, otherwise we leave them as they are.
         */
        Node left = find(leftNode);
        // left initialized the root of its tree
        Node right = find(rightNode);
        // right initialized the root of its tree

        if (right != null && left != null) {
            if (!left.value.equals(right.value)) {
//                // if true, they belong to different trees and we unite them
//                Node temp = leftNode;
//                while (!temp.value.equals(left.value)) {
//                    temp = temp.parent;
//                    leftNode.parent = left;
//                    leftNode = temp;
//                }
                left.parent = right;
            }
//            leftNode.parent = right;
            forest.remove(leftNode);
        }
    }

    public boolean areNodesConnected(Node left, Node right){
        /**
         * Here we search for the rootNodes of left and right nodes.
         * If they are equal, it means they belong to one tree -> return true
         * otherwise return false
         * */
        Node leftNode = find(left);
        Node rightNode = find(right);
        if (leftNode == null || rightNode == null){
            return false;
        }
        return leftNode.value.equals(rightNode.value);
    }

    public Node getNode(Node node){
        return nodeList.stream().filter(s -> s.value.equals(node.value)).findAny().orElse(null);
    }

    @Override
    public String toString() {
        return "DisjointSetUnion\n{\n" +
                "forestSize=" + forest.size() +
                "\n}";
    }
}


class Node{
    public Node parent;
    public String value;
    public boolean hit;

    public Node(String value){
        this.value = value;
        parent = null;
        hit = false;
    }

    @Override
    public String toString() {
        String parentValue = parent == null ? "null" : parent.value;
        return "Node{" +
                "value='" + value + '\'' +
                ", parent=" + parentValue +

                '}';
    }
}
