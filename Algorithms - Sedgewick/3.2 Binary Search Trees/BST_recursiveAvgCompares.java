/*

  3.2.7
  Basic implementation of a binary search tree.
  int avgCompares: Recursively computes the average number of compares 
  required by a random search hit.

*/

import java.util.Iterator;

public class BST_recursiveAvgCompares<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
    
        public Node(Key key, Value val, int N ) {
            this.key = key; this.val = val; this.N = N;
        }
    }
    
    public int size() { return size(root); }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // Return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.left);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);

        Node t = floor(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // eager Hibbard deletion
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
        
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int height() {
        return height(root, 0);
    }

    private int height(Node node, int height) {
        if (node == null) return height-1;
        int lh = height(node.left, height+1);
        int rh = height(node.right, height+1);
        return (lh >= rh) ? lh : rh;
    }

    public float avgCompares() {
        // internal path length / size + 1
        return ((float)internalPathLength() / (float)size()) + 1;
    }

    public int internalPathLength() {
        // find the internal path length of the tree, i.e the sum of the
        // depth of all nodes
        return internalPathLength(root, 0);
    }

    private int internalPathLength(Node node, int depth) {
        if (node == null) return 0;
        return depth +
            internalPathLength(node.left, depth+1) +
            internalPathLength(node.right, depth+1);
    }
            
        
    public static void main(String[] args) {
        String[] sb = "HCAESRX".split(""); // best case, balanced
        String[] sw = "XSRHECA".split(""); // worst case
        BST_recursiveAvgCompares bst = new BST_recursiveAvgCompares();
        for (int i = 0; i < sb.length; i++) {
             bst.put(sb[i],i);
        }
        System.out.println(bst.height());
        System.out.println(bst.size());
        System.out.println(bst.avgCompares());
    }
    
}

