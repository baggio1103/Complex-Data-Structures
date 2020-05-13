package IntervalTrees;

import java.util.Arrays;

public class IntervalTree {
    Node root;
    int[][] intervals;

    public IntervalTree(int[][] intervals){
        this.intervals = intervals;
        int[] i = intervals[0];
        root = new Node(i[0], i[1]);
        construct();
    }

    public void construct(){//returns root
        for (int i = 1; i < intervals.length; i++){
            int[] borders = intervals[i];
            //borders[0] = low, borders[1] = high
            addNode(borders[0], borders[1]);
        }
    }

    public void addNode(int low, int high){
        boolean added = false;
        Node node = root;
        while (!added){
            node.setMax(high);
            if (node.low > low){
                if (node.left != null){
                    node = node.left;
                }else {
                    node.left = new Node(low, high);
                    added = true;
                }
            }else {
                if (node.right != null){
                    node = node.right;
                }else {
                    node.right = new Node(low, high);
                    added = true;
                }
            }
        }
    }

    public void traverse(Node node){
        if (node == null){
            return;
        }
//        System.out.println("low : " + node.low + ", high : " + node.high);
        traverse(node.left);
        System.out.println("[ " + node.low + ", " + node.high + " ] max : " + node.max);
        traverse(node.right);
    }

    public int[] search(int[] borders){
        Node node = root;
        int low = borders[0];
        int high = borders[1];

        int[] intervals = new int[2]; // it will contains node intervals
        while (node != null){
            if (define(node, low, high)){
                intervals[0] = node.low;
                intervals[1] = node.high;
                return intervals;
            }else {
                if (node.high < low){
                    node = node.right;
                }else if (node.low > high){
                    node = node.left;
                }
            }
        }
        return null;
    }

    public boolean define(Node node, int low, int high){
        if (node.low < low && node.high > high){
            return true;
        }else if (node.low > low && node.low < high){
            return true;
        }

        if (node.high > low && node.high < high){
            return true;
        }else return node.low > low && node.low < high;
    }
    
}

class Node{
    public int high;
    public int low;
    public int max;
    public Node left;//left child
    public Node right;//right child

    //low and high borders of the interval
    // max - max value in the given interval
    public Node(int low, int high){
        this.low = low;
        this.high = high;
        this.max = high;
        right = left = null;
    }

    public void setMax(int max) {
        if (this.max < max) {
            this.max = max;
        }
    }
}
