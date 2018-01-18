import java.util.Iterator;

public class LinearProbingHashST_DoubleHashing<Key, Value> {
    private int N = 0;
    private int M = 0;
    private Key[] keys;
    private Value[] vals;

    // possible table sizes
    private final int[] primes = { 5, 11, 23, 47, 97, 193, 389, 787, 1571 };
    private int primeIndex = 0;
    
    public LinearProbingHashST_DoubleHashing() {
        M = primes[primeIndex];
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST_DoubleHashing(int primeIndex) {
        this.primeIndex = primeIndex;
        M = primes[primeIndex];
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public int size() { return N; }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private int relativePrime(Key key) {

        int h = hash(key);
        // h has to be a relative prime of M
        while (h == 0 || h % M == 0) {
            h++;
        }
        return h;
    }

    // increase or decrease table size according to the array of primes
    private void resize(boolean increase) {
        if (increase && primeIndex < primes.length-1) {
            primeIndex++;
        }
        if (!increase && primeIndex > 0) {
            primeIndex--;
        }
        LinearProbingHashST_DoubleHashing<Key, Value> t;
        t = new LinearProbingHashST_DoubleHashing<Key, Value>(primeIndex);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;    }

    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
            
        if (N >= M/2) {
            resize(true);
        }

        int i;
        int k = relativePrime(key);

        // we're not using linear probing anymore, we use a probing
        // sequence to find the next open slot
        for (i = hash(key); keys[i] != null; i = (i+k) % M) {
            if (keys[i].equals(key)) { vals[i] = val; return; }
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        int k = relativePrime(key);
        for (int i = hash(key); keys[i] != null; i = (i+k) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    // Exercise 3.4.29
    public void delete(Key key) {
        if (get(key) == null) return;
        
        int k = relativePrime(key);
        int i = hash(key);
        
        // move pointer to the node-to-be-deleted
        while(keys[i] != null && keys[i] != key) { i = (i+k)%M; }

        // delete node
        keys[i] = null;
        vals[i] = null;

        // move pointer to the next node in the sequence
        i = (i + k) % M;
        
        // rehash all elements in the cluster
        while (i < M && keys[i] != null) {
            Key nextKey = keys[i];
            Value nextVal = vals[i];
            put(nextKey, nextVal);
            i = (i+k)%M;
        }
        N--;

        if (N > 0 && N <= M/4) resize(false);
        
    }
    
    public void print() {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) StdOut.print("_ ");
            else StdOut.print(keys[i] + " ");
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        String s = "AECDBFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxzyA";

        LinearProbingHashST_DoubleHashing<Character, Integer> st
            = new LinearProbingHashST_DoubleHashing<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
             st.put(s.charAt(i),i);
        }

        for (int i = 0; i < s.length()-2; i++) {
            st.delete(s.charAt(i));
        }
        st.delete('a');
        st.print();
 
    }

}
                      
