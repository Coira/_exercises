/*
  Basic implementation of a binary search tree.
*/

import java.util.Iterator;

public class BSTWithDraw<Key extends Comparable<Key>, Value> {
    private Node root;

    private double nodeRadius = 0.02;
    private double dx, dy;
    
    public class Node {
        public Key key;
        public Value val;
        private Node left, right;
        private int N;
        private double x, y;
        private int p;
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

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
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

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // Return Node containing key of rank k.
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // Return number of keys less than key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
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

        Node t = ceiling(x.left, key);
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

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
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

    public Queue<Node> preorderTraversal() {
        Queue<Node> queue = new Queue<Node>();
        preorderTraversal(root, queue);
        return queue;
    }

    private void preorderTraversal(Node node, Queue<Node> queue) {
        if (node == null) return;

        queue.enqueue(node);
        preorderTraversal(node.left, queue);
        preorderTraversal(node.right, queue);

    }

    public Queue<Node> postorderTraversal() {
        Queue<Node> queue = new Queue<Node>();
        postorderTraversal(root, queue);
        return queue;
    }

    private void postorderTraversal(Node node, Queue<Node> queue) {
        if (node == null) return;

        postorderTraversal(node.left, queue);
        postorderTraversal(node.right, queue);
        queue.enqueue(node);
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
  
    public void printLevel() {
        Queue<Key> q = new Queue<Key>();
        printLevel(root,q);
        while (!q.isEmpty()) {
            StdOut.println(q.dequeue());
        }
    }

    private void printLevel(Node n, Queue<Key> q) {
        if (n != null) {
            printLevel(n.left,q);
            q.enqueue(n.key);
            printLevel(n.right,q);
        }
    }


    private int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }


    public void draw() {
        
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setScale(-.05, 1.05);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenRadius(0.004);
        dx = (1.0 / size());
        dy = 0.05;
        setCoords(root, 0);
        draw(root);
        StdDraw.show();
    }


    private double setCoords(Node n, int level) {
        if (n == null) { return -1; }

        int rank = rank(n.key);
        double posX = dx * rank;
        double posY = 1.0-(level*dy);
        
        double lc = setCoords(n.left, level+1);
        double rc = setCoords(n.right, level+1);
        
        if (lc >= 0 && rc >= 0 ) {
            posX = (lc + rc) / 2.0; // centre node above children
        }

        n.x = posX;
        n.y = posY;
        return posX;
    }
    
    private void draw(Node n) {
        if (n != null) {
            if (n.left != null) {
                drawLine(n.x, n.y, n.left.x, n.left.y);
                draw(n.left);
            }
            if (n.right != null) {
                drawLine(n.x, n.y, n.right.x, n.right.y);
                draw(n.right);
            }
            
            drawNode(n, n.x, n.y);
        }
    }
    
    private void drawNode(Node n, double x, double y) {
        if (n == null) return;

        double tx = 0.000000025;
        double ty = 0.002;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(x, y, nodeRadius);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, nodeRadius);
        StdDraw.text((x-tx), y-ty, (String)n.key);
    }

    private void drawLine(double x0, double y0, double x1, double y1) {
        StdDraw.line(x0,y0,x1,y1);
    }
    
    public static void main(String[] args) {
        String[] str = "HDBACFEGLJIKNMO".split("");
  
        BSTWithDraw<String, Integer> bst = new BSTWithDraw<String, Integer>();
        for (int i = 0; i < str.length; i++) {
            bst.put(str[i], i);
        }
        bst.draw();
    }
}
             



