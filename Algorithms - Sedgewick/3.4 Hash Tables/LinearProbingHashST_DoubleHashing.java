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
        M = primes[primeIndex];
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
        this.primeIndex = primeIndex;
    }
    
    private int hash(Key key) {
        // return (key.hashCode() & 0x7fffffff) % M;
        return 0;
    }

    private int relativePrime(char c) {
        int charIndex;
        
        if (Character.isUpperCase(c)) charIndex = (c - 'A') + 1;
        else charIndex = (c - 'a') + 1;

        // make sure return value is a relative prime of M
        if (charIndex % M == 0) return charIndex+1;
        return charIndex;
            
    }
    
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
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M/2) resize(true);

        int i;
        int k = relativePrime((char)key);

        // we're not using linear probing anymore, we use a probing
        // sequence to find the next open slot
        for (i = hash(key); keys[i] != null; i = (i+k) % M)
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        int k = relativePrime((char)key);
        for (int i = hash(key); keys[i] != null; i = (i+k) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public static int index(char c) {
        if (Character.isUpperCase(c)) return (c - 'A') + 1;
        else return (c - 'a') + 1;
    }
    
    public static void main(String[] args) {
        String s = "AECDBFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxzyA";

        LinearProbingHashST_DoubleHashing<Character, Integer> st
            = new LinearProbingHashST_DoubleHashing<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
             st.put(s.charAt(i),i);
        }
        
        
    }

}
                      
