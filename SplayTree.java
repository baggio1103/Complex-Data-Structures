public class SplayTree {
    Node root; 
    
    public SplayTree(Node root){
        this.root = root;
    }

    //possible two outcomes :
    // a) tree already contains the value and splay that node
    // b) add a new Node and splay the new Node
    public void add(int value){
        Node treeNode = search(value);
        if (treeNode.value != value){
            Node nodeToAdd = new Node(value);
            if (value > treeNode.value){
                treeNode.rightChild = nodeToAdd;
            }else {
                treeNode.leftChild = nodeToAdd;
            }
            nodeToAdd.parent = treeNode;
            splay(nodeToAdd);
        }else {
            splay(treeNode);
        }
    }

    public void remove(int value){
        Node nodeToRemove = search(value);
        splay(nodeToRemove);
        SplayTree leftSubtree = new SplayTree(nodeToRemove.leftChild);
        SplayTree rightSubtree = new SplayTree(nodeToRemove.rightChild);
        SplayTree newTree = merge(leftSubtree, rightSubtree);
        root = newTree.root;
    }

    public Node find(int value){
        Node node = search(value);
        splay(node);
        return node;
    }

    public void splay(Node node){
        while (node != root){
            if (node.parent == root){
                zig(node, root);
            }else if (isZigZig(node, node.parent)){
                    zigZig(node, node.parent);
            }else {
                zigZag(node, node.parent);
            }
        }
    }

    public void zigZag(Node node, Node parentNode){
        Node grandNode = getParent(parentNode);
        if (isRightChild(parentNode, getParent(parentNode)) && isLeftChild(node, parentNode)){
            rotateRight(node, parentNode);
            rotateLeft(node, grandNode);
        }else {
            rotateLeft(node, parentNode);
            rotateRight(node, grandNode);
        }
    }

    public void zig(Node node, Node parent){
        if (isLeftChild(node, parent)){
            rotateRight(node, parent);
        }
        if (isRightChild(node, parent)){
            rotateLeft(node, parent);
        }
    }

    public void zigZig(Node node, Node parent){
        Node grandNode = getParent(parent);
        if (isRightChild(node, parent) && isRightChild(parent, grandNode)){
            rotateLeft(parent, grandNode);
            rotateLeft(node, parent);
        }else {
            rotateRight(parent, grandNode);
            rotateRight(node, parent);
        }
    }

    public void rotateLeft(Node node, Node parentNode){
        isGrandNodeNotRoot(node, parentNode);
        parentNode.rightChild = node.leftChild;
        if (node.leftChild != null) {
            node.leftChild.parent = parentNode;
        }
        node.leftChild = parentNode;
        parentNode.parent = node;
    }

    public void rotateRight(Node node, Node parentNode){
        isGrandNodeNotRoot(node, parentNode);
        parentNode.leftChild = node.rightChild;
        if (node.rightChild != null) {
            node.rightChild.parent = parentNode;
        }
        node.rightChild = parentNode;
        parentNode.parent = node;
    }
    
    public SplayTree merge(SplayTree leftTree, SplayTree rightTree){
        if (leftTree.root != null && rightTree.root != null){
            Node maxNode = getMax(leftTree.root);
            splay(maxNode);
            maxNode.rightChild = rightTree.root;
            rightTree.root.parent = maxNode;
            return new SplayTree(maxNode);
        }
        if (leftTree.root == null){
            rightTree.root.parent = null;
            return rightTree;
        }else {
            leftTree.root.parent = null;
            return leftTree;
        }
    }

    public Node getMin(Node node){
        if (node.leftChild == null){
            return node;
        }
        return getMin(node.leftChild);
    }

    public Node getMax(Node node){
        if (node.rightChild == null){
            return node;
        }
        return getMax(node.rightChild);
    }

    public Node search(int value){
        Node node = root;
        while (node.value != value){
            if (value > node.value){
                if (node.rightChild != null){
                    node = node.rightChild;
                }else {
                    return node;
                }
            }else {
                if (node.leftChild != null){
                    node = node.leftChild;
                }else {
                    return node;
                }
            }
        }
        return node;
    }

    public void isGrandNodeNotRoot(Node node, Node parentNode){
        Node grandNode;
        if (parentNode != root){
            grandNode = getParent(parentNode);
            if (grandNode.leftChild == parentNode){
                grandNode.leftChild = node;
            }else {
                grandNode.rightChild = node;
            }
            node.parent = grandNode;
        }else {
            node.parent = null;
            root = node;
        }

    }

    public Node getParent(Node node){
        return node.parent;
    }

    public boolean isRightChild(Node node, Node parent){
        return parent.rightChild == node;
    }

    public boolean isLeftChild(Node node, Node parent){
        return parent.leftChild == node;
    }

    public boolean isZigZig(Node node, Node parent){
        if (isRightChild(node, parent) && isRightChild(parent, getParent(parent))){
            return true;
        }else
            return isLeftChild(node, parent) && isLeftChild(parent, getParent(parent));
    }
}

class Node{
    int value;
    Node leftChild;
    Node rightChild;
    Node parent;
    public Node(int value){
        this.value = value;
        leftChild = rightChild = parent = null;
    }

}
