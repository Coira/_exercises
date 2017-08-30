public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        // array resizing omitted
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() { return N; }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(Key key) { return get(key) != null; }
    
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }

    public int rank(Key key) {
        // non-recursive binary search
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        if (contains(key)) {
            int p = rank(key);
            for (int i = p+1; i < N; i++) {
                keys[i-1] = keys[i];
                vals[i-1] = vals[i];
            }
        }
        N--;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N-1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (keys[i] != key) {
            if (i == 0) { return null; }
            else { return keys[i-1]; }
        }
        return key;
    }

    public void show() {
        for (int i = 0; i < N; i++) {
            StdOut.print("(" + keys[i] + "," + vals[i] + ") ");
        }
        StdOut.println();
    }
    //public Key floor(Key key) {
        // exercise 3.1.17
    //}


    /*
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    } 
    */

    public static void main(String[] args) {
        BinarySearchST<Integer, String> st
            = new BinarySearchST<Integer, String>(10);

        st.put(1, "a");
        st.put(1, "b");
        st.put(3, "c");

        StdOut.println(st.floor(2));
        StdOut.println(st.floor(9));
    }
    
}
