import java.util.Iterator;

public class LinearProbingHashST_LazyDelete<Key, Value> {
    private int N = 0; // number of keys + null keys in table
    private int n = 0; // number of keys (not including keys with null vals)
    private int M = 4;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST_LazyDelete() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST_LazyDelete(int cap) {
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
        M = cap;
    }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        // StdOut.println(M + " --> " + cap);
        LinearProbingHashST_LazyDelete<Key, Value> t;
        t = new LinearProbingHashST_LazyDelete<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null && vals[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
        N = t.N;
        n = t.n;
    }

    public void put(Key key, Value val) {
        // even keys with nulls add to load, so resize when table is over half full
        if (N >= M/2) {
            if (n <= M/4) {      // fewer than 1/4 full, halve size
                resize(M/2);
            }
            else if (n < M/2) {  // table is less than half full, just remove nulls
                resize(M);
            }
            else {
                resize(M*2);     // double table size
            }
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key)) {
                if (vals[i] == val) {  } // no change to table
                else if (val == null) {  // mark for deletion
                    StdOut.println("Marking " + key + " for deletion");
                    vals[i] = val;
                    n--;
                }
                else if (vals[i] == null) { // replacing null with value
                    vals[i] = val;
                    n++;
                }
            
                return;
            }
        }

        // add new pair
        keys[i] = key;
        vals[i] = val;
        N++;
        n++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    // Exercise 3.4.19
    public Iterable<Key> keys() {
        return new HashTableIterable();
    }

    private class HashTableIterable implements Iterable<Key> {
        public Iterator<Key> iterator() {
            return new Iterator<Key>() {
                private int i = 0;
                private int count = 0;
                public boolean hasNext() { return count < n; }
                public Key next() {
                    while (keys[i] == null || vals[i] == null) {
                        i++;
                    }
                    count++;
                    return keys[i++];
                }
            };
        }
    }
                 
    public static void main(String[] args) {
        String s = "ABCDEFGHIJKLM";
        LinearProbingHashST_LazyDelete<Character, Integer> st
            = new LinearProbingHashST_LazyDelete<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            st.put(s.charAt(i),i);
        }
    }

}
                      
