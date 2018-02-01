import java.util.Iterator;

public class HashSTint<Value> {
    private int N = 0;
    private int M = 16;
    private Integer[] keys;
    private Value[] vals;

    public HashSTint() {
        keys = new Integer[M];
        vals = (Value[]) new Object[M];
    }

    public HashSTint(int cap) {
        keys = new Integer[cap];
        vals = (Value[]) new Object[cap];
        M = cap;
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
            
    private int hash(Integer key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        HashSTint<Value> t;
        t = new HashSTint<Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null && vals[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Integer key, Value val) {
        // eager delete
        if (val == null) {
            delete(key);
            return;
        }

        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null ; i = (i+1) % M)
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Integer key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(key) && vals[i] != null)
                return vals[i];
        return null;
    }

    public void delete(Integer key) {
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
        for (int i = (position+1) % M; keys[i] != null; i = (i+1) % M) {
            int k = keys[i];
            Value v = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(k, v);
        }
    }
            
        
    // Exercise 3.4.19
    public Iterable<Integer> keys() {
        return new HashTableIterable();
    }

    private class HashTableIterable implements Iterable<Integer> {
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int i = 0;
                private int count = 0;
                public boolean hasNext() { return count < N; }
                public Integer next() {
                    while (keys[i] == null) {
                        i++;
                    }
                    count++;
                    return keys[i++];
                }
            };
        }
    }

    public static void main(String[] args) {
        HashSTint<Integer> st = new HashSTint<Integer>();
        
        int[] ints = {1,2,4,5,7,3,4};
        for (int i = 0; i < ints.length; i++) {
            st.put(ints[i], i);
        }
        StdOut.println(st.size());
        for (int i = 0; i < ints.length; i++) {
            st.delete(ints[i]);
        }

        for (int x : st.keys()) {
            StdOut.print(x + " ");
        }
        StdOut.println(st.size());
    }
                       
            
}
                      
