import java.util.Iterator;

public class HashST<Key, Value> {
    private int N = 0;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public HashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public HashST(int cap) {
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
        M = cap;
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        HashST<Key, Value> t;
        t = new HashST<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void delete(Key key) {
        if (get(key) == null) return;
        
        if (N > 0 && N <= M/4) resize(M/2);
        
        int position = 0;
        // find position of key to be deleted
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key)) position = i;
        }

        // delete keys
        keys[position] = null;
        vals[position] = null;
        N--;
        
        // reinsert all elements in that cluster
        for (int i = position+1; i < N && keys[i] != null; i = (i+1) % M) {
            Key k = keys[i];
            Value v = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(k, v);
        }
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
                public boolean hasNext() { return count < N; }
                public Key next() {
                    while (keys[i] == null) {
                        i++;
                    }
                    count++;
                    return keys[i++];
                }
            };
        }
    } 
}
                      
