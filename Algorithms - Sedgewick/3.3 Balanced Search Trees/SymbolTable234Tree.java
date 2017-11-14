public class SymbolTable234Tree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean colour;

        Node(Key key, Value val, int N, boolean colour) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.colour = colour;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.colour == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.colour = h.colour;
        h.colour = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.colour = h.colour;
        h.colour = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColours(Node h) {
        h.colour = RED;
        h.left.colour = BLACK;
        h.right.colour = BLACK;
    }

    public int size() { return size(root); }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.colour = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) // Do standard insert, with red link to parent
            return new Node(key, val, 1, RED);

        // moving this line here changes this tree to a 234 tree
        if (isRed(h.left) && isRed(h.right))     flipColours(h);
        
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        //  if (isRed(h.left) && isRed(h.right))     flipColours(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private int height() {
        Node curr = root;
        int h = 0;
        while (curr != null) {
            if (curr.colour == BLACK) {
                System.out.print(curr.key + " ");
                h++;
            }
            curr = curr.right;
        }
        return h;
    }

    public Key delMin() {
        if (!isRed(root.left) && // root is a 2-node
            (!isRed(root.left.left) && !isRed(root.right.left))) {
                // both children are 2-nodes, convert to 4-node
                flipColours(root.left);
                flipColours(root.right);
        }
        else {
            
        // delMin(root);
    }

    private Key delMin(Node h) {
        if (h.left.left == null) {
            h.left = h.left.right;
        }
                
    }

    public static void main(String[] args) {
        SymbolTable234Tree<Integer, String> t =
            new SymbolTable234Tree<Integer,String>();
        t.put(0, "a");
        t.put(1, "b");
        StdOut.println(t.size());
    }
}
