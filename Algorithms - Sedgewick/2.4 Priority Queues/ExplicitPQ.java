/*
  2.4.24
  Max-value linked list Priority Queue with explicit links.
*/

import java.util.LinkedList;
import java.util.Queue;

public class ExplicitPQ<Key extends Comparable<Key>> {
    private class Node {
        Node parent;
        Node left;
        Node right;
        Key key;
    }

    private Node root;
    private Node tail; // insert() adds to this node's children
    private Node last;  // pointer to the last node added
    private int tpos = 0; // Node 0 is empty, root node will be at #1
    private int N;
    private Integer[] path = new Integer[1]; // path to tail node

    public ExplicitPQ() { }

    public ExplicitPQ(Key[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }
    
    public boolean isEmpty() { return root == null; }
    public int size() { return N; }

    public void insert(Key k) {
        Node n = new Node();
        n.key = k;
        n.parent = tail;
        if (isEmpty()) {
            root = n;
            tail = n;
            tpos = 1;
        }
        else {
            if (tail.left == null) {
                tail.left = n;
                swim(tail.left);
            }
            else {
                tail.right = n;
                swim(tail.right);
                moveTail(++tpos);
            }
        }
        N++;
    }

    // move tail to the node in position 'tpos'
    private void moveTail(int newPosition) {
        tpos = newPosition;

        // path from node to tpos
        createPath(tpos);

        tail = root;
        int i = 0;
        while (path[i] != -1) {
            if (path[i] % 2 == 0) tail = tail.left;
            else tail = tail.right;
            i++;
        }
    }

    private void swim(Node n) {
        Node current = n;
        Key temp;
        while (current.parent != null &&
               less(current.parent.key, current.key)) {
            exch(current, current.parent);
            current = current.parent;
        }
    }

    private void sink(Node n) {
        Node current = n;
        Key temp;

        // sink n by continually swapping it with its largest left
        // or right child node until its children has a smaller key
        
        while (current.left != null) {

            // right child is bigger
            if (current.right != null &&
                less(current.left.key, current.right.key)) {

                // swap with right child
                if (less(current.key, current.right.key))
                    exch(current, current.right);

                current = current.right;
            }
            else if (less(current.key, current.left.key)) {
                exch(current, current.left);
                current = current.left;
            }
            else {
                break;
            }
        }
    }

    public Key delMax() {

        Key key = root.key;
        Node node = root; 
        Node parent;
        
        if (size() == 1) {
            root = null;
            return key;
        }

        // go to the last node in the tree
        createPath(N);
        int i = 0;
        while (path[i] != -1) {
            if (path[i] % 2 == 0) node = node.left;
            else node = node.right;
            i++;
        }

        // replace root with last node and remove links
        root.key = node.key;
        parent = node.parent;
        if (parent.right != null) {
            if (parent.right == node)
                moveTail(--tpos);
            parent.right = null;
        }
        else
            parent.left = null;
        sink(root);

        N--;
        return key;
        
    }

    private void createPath(int nodePos) {
        
        // To get from nodePos to the root, we need to move
        // up the tree's levels. Therefore path.length should be
        // at least as large as the number of levels there are
        // *between* nodePos and root.
        
        int nodeLevel = (int)Math.ceil(Math.log10(nodePos+1)/Math.log10(2));
        if (nodeLevel != path.length)
            path = new Integer[nodeLevel]; 
                
        path[nodeLevel-1] = -1; // indicates path end
        for (int i = nodeLevel-2; i >= 0; i--) {
            path[i] = nodePos;
            nodePos /= 2;
        }
    }
    
    private void exch(Node a, Node b) {
        Key temp = a.key;
        a.key = b.key;
        b.key = temp;
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void show() {
        if (isEmpty()) { return; }
        show(root);
    }

    // print tree in order of top to bottom and left to right
    private void show(Node root) {
        Queue<Node> level = new LinkedList<>();
        level.add(root);
        while (!level.isEmpty()) {
            Node node = level.poll();
            System.out.print(node.key + " ");
            if (node.left != null)
                level.add(node.left);
            if (node.right != null)
                level.add(node.right);
        }

        System.out.println();
    }   
}
