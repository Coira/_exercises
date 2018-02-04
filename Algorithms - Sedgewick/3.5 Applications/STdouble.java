public class STdouble<Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    
    private class Node {
        Double key;
        Value val;
        Node left, right;
        int N;
        boolean colour;

        double x, y; // used for tree drawing
        
        Node(Double key, Value val, int N, boolean colour) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.colour = colour;
        }
    }
    
    private boolean isEmpty() { return root == null; }
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.colour == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.colour = x.left.colour;
        x.left.colour = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.colour = x.right.colour;
        x.right.colour = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColours(Node h) {
        h.colour = !h.colour;
        h.left.colour = !h.left.colour;
        h.right.colour = !h.right.colour;
    }

    public int size() { return size(root); }
    
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public void put(Double key, Value val) {
        root = put(root, key, val);
        root.colour = BLACK;
    }

    private Node put(Node h, Double key, Value val) {
        if (h == null) // Do standard insert, with red link to parent
            return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColours(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public Value get(Double key) {
        return get(root, key);
    }

    private Value get(Node x, Double key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public Iterable<Double> keys() {
        Queue<Double> q = new Queue<Double>();
        return keys(root, q);
    }

    private Iterable<Double> keys(Node n, Queue<Double> q) {
        if (n == null) return q;
        else {
            keys(n.left, q);
            q.enqueue(n.key);
            keys(n.right, q);
        }
        return q;
    }
             
    private int height() {
        Node curr = root;
        int h = 0;
        while (curr != null) {
            if (curr.colour == BLACK) {
                h++;
            }
            curr = curr.right;
        }
        return h;
    }

    public int rank(Double key) {
        return rank(key, root);
    }

    private int rank(Double key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Double select(int k) {
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

    public boolean contains(Double key) {
        return contains(key, root);
    }

    private boolean contains(Double key, Node x) {
        if (x == null) return false;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return contains(key, x.left);
        else if (cmp > 0) return contains(key, x.right);
        else return true;
    }
    
    private Node moveRedLeft(Node h) {
        // Assuming that h is red and both h.left adn h.left.lfet
        // are black, make h.left or one of its children red.
        flipColours(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColours(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        // Assuming that h is red and both h.right and h.right.left
        // are black, make h.right or one of its children red.
        flipColours(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColours(h);
        }
        return h;
    }

    private Node min(Node h) {
        if (h == null) return null;
        while (h.left != null) {
            h = h.left;
        }
        return h;
    }
    
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.colour = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) root.colour = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.colour = BLACK;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(Double key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = RED;
        root = delete(root, key);
        if (!isEmpty()) root.colour = BLACK;
    }

    private Node delete(Node h, Double key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
                
    private Node balance(Node h) {
        if (h == null) return null;

        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColours(h);
        
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
}
        
            
        
