/* 
   3.1.2 
   Unordered array implementation of a symbol table
*/

public class ArrayST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N; // number of items
    private int S; // actual array size (inc. null items)

    public ArrayST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Key[] tkeys = (Key[]) new Comparable[capacity];
        Value[] tvals = (Value[]) new Object[capacity];
        
        N = 0;
        for (int i = 0; i < S; i++) {
            // also delete null items
            if (vals[i] != null) {
                tvals[N] = vals[i];
                tkeys[N++] = keys[i];
            }
        }
        S = N;
        keys = tkeys;
        vals = tvals;
    }
                     
    public void put(Key key, Value val) {
        for (int i = 0; i < S; i++) {
            // key exists, replace value
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        // add new key-value
        if (keys.length == S) { resize(S*2); }
        keys[S] = key;
        vals[S++] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = 0; i < S; i++) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        // lazy delete, remove null items in resize()
        put(key, null);
        N--;
        if (N > 0 && N <= keys.length/4) { resize(keys.length/2); }
    }

    public boolean contains(Key key) {
        for (int i = 0; i < S; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (int i = 0; i < S; i++) {
            if (keys[i] != null) {
                q.enqueue(keys[i]);
            }
        }
        return q;
    }

    public static void main(String[] args) {
    }
}

    
