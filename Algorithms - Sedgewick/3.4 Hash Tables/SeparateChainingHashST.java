import java.util.Iterator;

public class SeparateChainingHashST<Key, Value> {
    private int M;   // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of ST objects
    private int N;  // number of keys
    
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

    public int size() { return N; }
    
    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        if (get(key) == null) { N++; }
        st[hash(key)].put(key, val);
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
                private Iterator<Key> keys = st[i].keys().iterator();
                
                public boolean hasNext() {
                    return (count < N);
                }
                public Key next() {
                    while (!keys.hasNext())
                        keys = st[++i].keys().iterator();
                    count++;
                    return keys.next();
                }
            };
        }
    }
    
    public static void main(String[] args) {
        String s = "SEARCHXMPLA";
        SeparateChainingHashST<Character, Integer> st
            = new SeparateChainingHashST<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            st.put(s.charAt(i),i);
        }
        Iterable<Character> it = st.keys();
        for (Character c : it) {
            StdOut.print(c + " ");
        }
    }
}


    

    
