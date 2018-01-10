import java.util.Iterator;

public class LinearProbingHashST_AvgCost<Key, Value> {
    private int N = 0;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST_AvgCost() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST_AvgCost(int cap) {
        keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
        M = cap;
    }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST_AvgCost<Key, Value> t;
        t = new LinearProbingHashST_AvgCost<Key, Value>(cap);
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

    // Exercise 3.4.20
    public double hitCost() {
        double alpha = (double)N/M;
        return (1.0/2.0)*(1.0 + (1.0/(1.0-alpha)));
    }

    // Exercise 3.4.21
    public double missCost() {
        double alpha = (double)N/M;
        return (1/2.0)*(1.0 + (1.0/Math.pow(1-alpha,2)));
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
                        
    public static void main(String[] args) {
        String s = "SEARCHXMPLAQWFPGJL";
        LinearProbingHashST_AvgCost<Character, Integer> st
            = new LinearProbingHashST_AvgCost<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            st.put(s.charAt(i),i);
        
            StdOut.println(st.hitCost());
            StdOut.println(st.missCost());
            StdOut.println();
        }
        
    }

}
                      
