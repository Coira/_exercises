import java.util.Iterator;

public class SeparateChainingHashST_AverageProbes<Key, Value> {

    private final int[] primes = {31, 61, 127, 251, 509, 1021, 2039, 4093,
                                  8191, 16381, 32749, 65521, 131071, 262139,
                                  524287, 1048587, 2097143, 4194301, 8388593,
                                  16777213, 33554393, 67108859, 134217689,
                                  268435399, 536870909, 1073741789, 2147483647};
    
    private int M;   // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of ST objects

    private double avgProbes; // average number of probes, exercise 3.4.18 
    private int N; // number of keys
    
    public SeparateChainingHashST_AverageProbes() {
        // this(997); // 997 lists
        this(1,5.0);
    }

    /*  M: initial table size
        avg: max number of average probes to be tolerated
    */
    public SeparateChainingHashST_AverageProbes(int M, double avg) {
        // Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
        this.avgProbes = avg;
    }

    private void resize(int size) {
        SequentialSearchST<Key, Value>[] temp =
            (SequentialSearchST<Key, Value>[]) new SequentialSearchST[size];

        // initialise table
        for (int i = 0; i < size; i++)
            temp[i] = new SequentialSearchST();

        // rehash and reinsert keys
        for (int i = 0; i < M; i++) {
            Iterator<Key> keys = st[i].keys().iterator();
            
             while (keys.hasNext()) {
                 Key key = keys.next();
                 Value val = st[i].get(key);
                 temp[hash(key)].put(key, val);
             }
        }
        this.M = size;
        st = temp;
    }
        
    
    private int hash(Key key) {
        int t = key.hashCode() & 0x7fffffff;
        double lgM = Math.log(M) / Math.log(2);
        if (lgM < 26) t = t % primes[(int)lgM+5];
        return t % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        if (N/M >= avgProbes) {
            resize(2*M);
        }
        // key already in table
        if (get(key) == null) {
            N++;
        }
        st[hash(key)].put(key, val);
    }

    /*
    public Iterable<Key> keys() {
        // See exercise 3.4.19
    }
    */

    public static void main(String[] args) {
        String s = "ARSTDHNEIOQWFPGJLUYZXCVBKM";
        SeparateChainingHashST_AverageProbes<Character, Integer> st
            = new SeparateChainingHashST_AverageProbes<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
             st.put(s.charAt(i),i);
        }

    }
}


    

    
