package BinomialHeap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinomialHeap {
    Node heapRoot;
    public BinomialHeap(Node node){
        this.heapRoot = node;
    }

    public BinomialHeap merge(BinomialHeap heap1, BinomialHeap heap2){
        LinkedList<Node> nodeList = new LinkedList<>();
        Node node1 = heap1.heapRoot;
        Node node2 = heap2.heapRoot;
        while (node1 != null && node2 != null){
            if (node1.count > node2.count){
                nodeList.add(node2);
                node2 = node2.nextSibling;
            }else {
                nodeList.add(node1);
                node1 = node1.nextSibling;
            }
        }
        if (node1 != null){
            nodeList.get(nodeList.size()-1).nextSibling = node1;
        }else {
            nodeList.get(nodeList.size()-1).nextSibling = node2;
        }
        return getBinomialHeap(nodeList);
    }

    public BinomialHeap unite(BinomialHeap heap){
        Node node = heap.heapRoot;
        List<Node> list = new ArrayList<>();
        while (node != null) {
            Node prevNode = node.previousSibling;
            Node nextNode = node.nextSibling;
            if (nextNode != null){
                if (node.count != nextNode.count){
                    list.add(node);
                    node = node.nextSibling;
                }else if (nextNode.nextSibling != null && nextNode.count == nextNode.nextSibling.count){
                    list.add(node);
                    node = node.nextSibling;
                }else if (node.value <= nextNode.value){
                    Node temp = nextNode.nextSibling;
                    Node leftChild = node.leftMostChild;
                    node.leftMostChild = nextNode;
                    node.count += nextNode.count;
                    nextNode.nextSibling = leftChild;
                    node.nextSibling = temp;
                    list.add(node);
                    node = node.nextSibling;
                }else {
                    Node leftChild = nextNode.leftMostChild;
                    nextNode.leftMostChild = node;
                    node.nextSibling = leftChild;
                    if (prevNode != null){
                        prevNode.nextSibling = nextNode;
                    }
                    nextNode.count += nextNode.leftMostChild.count;
                    node = nextNode;
                    list.add(nextNode);
                    node = node.nextSibling;
                }
            }
        }
        return getBinomialHeap(list);
    }

    public BinomialHeap add(BinomialHeap heap, Node node){
        BinomialHeap newHeap = new BinomialHeap(node);
        return unite(merge(heap, newHeap));
    }

    public BinomialHeap getBinomialHeap(List<Node> list){
        Node node = list.get(0);
        for (int i = 1; i < list.size(); i++){
            node.nextSibling = list.get(i);
        }
        return new BinomialHeap(node);
    }
}

class Node{
    Node nextSibling;
    Node previousSibling;
    Node leftMostChild;
    int value;
    int count;
    public Node(int value){
        this.value = value;
        count = 1;
        nextSibling = previousSibling = leftMostChild = null;
    }

}
