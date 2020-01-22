import java.util.ArrayList;
import java.util.Stack;

public class EulerTree {
    public Node root;
    public ArrayList<Node> euler = new ArrayList();
    public ArrayList<Integer> levels = new ArrayList();

    public EulerTree(Node node){
        this.root = node;
    }

    public void EulerTour(){
        Stack<Node> stack = new Stack<>();
        Node node = root;
        stack.push(node);
        node.hit = true;
        euler.add(node);
        levels.add(node.level);
        node.occurrence = euler.size()-1;
        while (stack.size() != 0){
            if (node.left != null && !node.left.hit){
                node = node.left;
                stack.push(node);
                node.hit = true;
                euler.add(node);
                node.occurrence = euler.size()-1;
                levels.add(node.level);
            }else if (node.right != null && !node.right.hit){
                node = node.right;
                stack.push(node);
                node.hit = true;
                euler.add(node);
                node.occurrence = euler.size()-1;
                levels.add(node.level);
            }else {
                stack.pop();
                if (stack.size() != 0) {
                    node = stack.peek();
                    euler.add(stack.peek());
                    node.occurrence = euler.size()-1;
                    levels.add(node.level);
                }
            }
        }
    }

    public void print(int func){
        // func == 1 - print nodeValues
        //func == 2 - print nodeOccurrences
        // func == 3 - print nodeLevels
        if (func == 1) {
            for (int i = 0; i < euler.size(); i++) {
                System.out.print(euler.get(i).value + "  ");
            }
        }else if (func == 2){
            for (int i = 0; i < euler.size(); i++) {
                System.out.print(euler.get(i).occurrence + "  ");
            }
        }else if (func == 3){
            for (int i = 0; i < euler.size(); i++) {
                System.out.print(euler.get(i).level + "  ");
            }
        }
        System.out.println();
    }

    public Node LCA(Node node1, Node node2){
        int min;
        if (node1.occurrence < node2.occurrence){
             min = node1.occurrence;
            for (int i = node1.occurrence+1; i < node2.occurrence; i++){
                if (min > euler.get(i).level){
                    min = euler.get(i).level;
                }
            }
        }else {
             min = node2.occurrence;
            for (int i = node2.occurrence+1; i < node1.occurrence; i++){
                if (min > euler.get(i).level){
                    min = euler.get(i).level;
                }
            }
        }
        return euler.get(min);
    }


    public static void main(String[] args) {
        Node root = new Node(null, 1);
        root.left = new Node(root, 2);
        root.right = new Node(root, 3);
        root.left.left = new Node(root.left, 4);
        root.left.right = new Node(root.left, 5);
        root.right.left = new Node(root.right, 6);
        root.right.right = new Node(root.right, 7);

//   Illustration               1
//                            /   \
//                           /     \
//                          2        3
//                        /   \     /  \
//                       4     5    6   7


        EulerTree tree = new EulerTree(root);
        tree.EulerTour();
        System.out.println("values");
        tree.print(1);
        System.out.println("occurrences");
        tree.print(2);
        System.out.println("levels");
        tree.print(3);
        System.out.println(tree.root.left.left.occurrence + " is index occurrence of 4");
        System.out.println(tree.root.left.right.occurrence + " is index occurrence of 5");
        System.out.println(tree.LCA(root.left.left, root.left.right).value == 2); //  4, 5 -> 2

        System.out.println(tree.root.left.left.occurrence + " is index occurrence of 4");
        System.out.println(tree.root.right.right.occurrence + " is index occurrence of 7");
        System.out.println(tree.LCA(root.left.left, root.right.right).value == 1); // 4, 7 -> 1

        System.out.println(tree.root.left.occurrence + " is index occurrence of 2");
        System.out.println(tree.root.right.left.occurrence + " is index occurrence of 6");
        System.out.println(tree.LCA(root.left, root.right.left).value == 1); // 2, 6 -> 1


    }

}


class Node{
    public int value;
    public Node left; // left child of a node
    public Node right;// right child of a node
    public Node parent;// parent of a node
    public int level; // level or height of a node
    public boolean hit; // predicate whether a node is visited or not
    public int occurrence; // efficient when it is need to get the index of a node from an array after euler tour

    public Node(Node parent, int data){
        this.value = data;
        left = right = null;
        this.parent = parent;
        hit = false;
        level = getLevel(parent);
    }

    public int getLevel(Node node){
        int height = 0;
        while (node != null){
            height ++;
            node = node.parent;
        }
        return height;
    }

}
