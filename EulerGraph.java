package EulerTree;

import java.util.ArrayList;
import java.util.List;

public class EulerGraph {
    Node root;
    List<Node> eulerRoute;
    public EulerGraph(Node node){
        this.root = node;
        eulerRoute = new ArrayList<>();
    }

    public List<Node> constructEulerRoute(Node node){
        List<Node> eulerRoute = new ArrayList<>();
        if (node.hasChildren()){
            traverseGraph(node, eulerRoute);
        }else {
            eulerRoute.add(node);
            eulerRoute.add(node);
        }
        return eulerRoute;
    }
    
    public void traverseGraph(Node node, List<Node> list){
        list.add(node);
        for (Node temp : node.childNodes){
            if (temp.hasChildren()){
                traverseGraph(temp, list);
            }else {
                list.add(temp);
            }
            list.add(node);
        }
    }

    public void printRoute(List<Node> list){
        for (Node node : list){
            System.out.print(node.value + " ");
        }
        System.out.println();
    }

    public void convertIntoGraph(Node node){
        for (Node node1 : node.childNodes){
            if (node1.hasChildren()){
                convertIntoGraph(node1);
            }
            node1.parent = node;
        }
    }

    public void addEdge(EulerGraph eulerGraph1, Node v1, EulerGraph eulerGraph2, Node v2){

        eulerGraph1.setRoot(v1); // Setting new Roots => v1 - is the new Root of the eulerGraph1

        eulerGraph2.setRoot(v2); // Setting new Roots => v2 - is the new Root of the eulerGraph2

        eulerGraph1.eulerRoute.addAll(eulerGraph2.eulerRoute); // connecting two EulerRoutes

        eulerGraph1.eulerRoute.add(v1); // adding v1 to the end of the final EulerRoute

    }

    public void removeEdge(Node v1, Node v2){
        setRoot(v1);
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        List<Node> list3 = new ArrayList<>();
        int v1_To_v2 = copyValues(0, list1, v2);
        int v2_To_v1 = copyValues(v1_To_v2, list2, v1);
        for (int i = v2_To_v1; i < eulerRoute.size(); i++){
            list3.add(eulerRoute.get(i));
        }
        list1.remove(list1.size()-1);
        eulerRoute.clear();
        eulerRoute.addAll(list2);
        eulerRoute.addAll(list1);
        eulerRoute.addAll(list3);
    }

    public int copyValues(int index, List<Node> list, Node node){
        for (int i = index; i < eulerRoute.size(); i++){
            if (eulerRoute.get(i) == node){
                return i;
            }
            list.add(eulerRoute.get(i));
        }
        return -1;
    }

    public void checkEulerRoute(){
        if (eulerRoute.size() == 0){
            eulerRoute = constructEulerRoute(root);
        }
    }

    public void setRoot(Node newRoot){
        checkEulerRoute();
        if (eulerRoute.get(0) != newRoot) {
            List<Node> leftSide = new ArrayList<>();
            List<Node> rightSide = new ArrayList<>();
            split(leftSide, rightSide, newRoot);
            leftSide.remove(0);
            connectRoutes(leftSide, rightSide);
            eulerRoute.add(newRoot);
        }
    }

    public void split(List<Node> leftSide, List<Node> rightSide, Node node){
        int index = eulerRoute.indexOf(node);
        for (int i = 0; i < index; i++){
            leftSide.add(eulerRoute.get(i));
        }
        for (int i = index; i < eulerRoute.size(); i++){
            rightSide.add(eulerRoute.get(i));
        }
    }

    public void connectRoutes(List<Node> leftSide, List<Node> rightSide){
        eulerRoute.clear();
        eulerRoute.addAll(rightSide);
        eulerRoute.addAll(leftSide);
    }

    public boolean doTheyBelongToOneGraph(Node v1, Node v2){
        checkEulerRoute(); // ensuring that EulerRoute is built
        return eulerRoute.contains(v1) && eulerRoute.contains(v2);
    }

    public List<Node> getEulerRoute(){
        checkEulerRoute();
        return eulerRoute;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node nodeD = new Node(5);
        node.childNodes.add(nodeD);
        nodeD.childNodes.add(new Node(3));
        Node node3 = new Node(7);
        nodeD.childNodes.add(node3);
        Node node4 = new Node(9);
        Node node2 = new Node(11);
        node3.childNodes.add(node4);
        node3.childNodes.add(node2);
        EulerGraph graph = new EulerGraph(node);
        List<Node> list = graph.constructEulerRoute(node);
        graph.printRoute(list);
        graph.setRoot(node3);
        list = graph.eulerRoute;
        graph.printRoute(list);
    }

}

class Node{
    int value;
    List<Node> childNodes;
    Node parent;
    public Node(int value){
        this.value = value;
        childNodes = new ArrayList<>();
        parent = null;
    }

    public boolean hasChildren(){
        return childNodes.size() > 0;
    }

}
