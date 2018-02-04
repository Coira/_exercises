public class RedBlackBSTWithDraw<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    
    private double dx, dy; // used for tree drawing
    private double nodeRadius = 0.03;
    
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean colour;

        double x, y; // used for tree drawing
        
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

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }
    
    public void draw() {
        StdDraw.setScale(-.05, 1.05);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenRadius(0.004);
        dx = (1.0 / size());
        dy = 0.07;
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
                drawLine(n, n.left);
                draw(n.left);
            }
            if (n.right != null) {
                drawLine(n, n.right);
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

    private void drawLine(Node parent, Node child) {
        if (parent == null || child == null) return;
        if (isRed(child)) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.01);
        }
        StdDraw.line(parent.x, parent.y, child.x, child.y);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.004);
    }
    
    private void drawLine(double x0, double y0, double x1, double y1) {
        StdDraw.line(x0,y0,x1,y1);
    }

    public static void main(String[] args) {
        String[] s = "HDBACFEGLJIKNM".split("");
        RedBlackBSTWithDraw<String, Integer> bst
            = new RedBlackBSTWithDraw<String, Integer>();
        for (int i = 0; i < s.length; i++) {
            bst.put(s[i], i);
        }
        bst.draw();
    }
        
    // See exercises for delete and delete min
}
        
            
        
