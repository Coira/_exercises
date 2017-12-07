public class SeparateChainingHashST<Key, Value> {
    private int M;   // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of ST objects

    public SeparateChainingHashST() {
        this(997); // 997 lists
    }

    public SeparateChainingHashST(int M) {
        // Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    /*
    public Iterable<Key> keys() {
        // See exercise 3.4.19
    }
    */

    public static void main(String[] args) {
        String s = "SEARCHXMPL";
        SeparateChainingHashST<Character, Integer> st
            = new SeparateChainingHashST<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            st.put(s.charAt(i),i);
        }
    }
}


    

    
