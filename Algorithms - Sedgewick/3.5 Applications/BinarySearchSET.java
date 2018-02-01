public class BinarySearchSET<Key extends Comparable<Key>> {
    private Key[] keys;
    private int N;

    public BinarySearchSET() {
        keys = (Key[]) new Comparable[2];
    }
    
    public BinarySearchSET(int capacity) {
        keys = (Key[]) new Comparable[capacity];
    }

    private void resize(int cap) {
        Key[] temp = (Key[]) new Comparable[cap];
        for (int i = 0; i < N; i++) {
            temp[i] = keys[i];
        }
        keys = temp;
    }
        
        
    public int size() { return N; }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(Key key) {
        int i = rank(key);
        return i < N && keys[i].compareTo(key) == 0;
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

    public void add(Key key) {
        if (N == keys.length) resize(keys.length*2);
        
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
        }
        keys[i] = key;
        N++;
    }

    public void delete(Key key) {
        if (N > 0 && N == keys.length/4) resize(keys.length/2);
        
        if (contains(key)) {
            int p = rank(key);
            for (int i = p+1; i < N; i++) {
                keys[i-1] = keys[i];
            }
            N--;
        }
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

    public static void main(String[] args) {
        String[] str = "AWKFHCNH".split("");
        BinarySearchSET<String> set = new BinarySearchSET<String>();
        for (int i = 0; i < str.length; i++)
            set.add(str[i]);
        StdOut.println(set.size());

        StdOut.println(set.contains("A"));
        StdOut.println(set.contains("X"));
        
        for (int i = 0; i < str.length; i++)
            set.delete(str[i]);
        StdOut.println(set.size());

    }
}
