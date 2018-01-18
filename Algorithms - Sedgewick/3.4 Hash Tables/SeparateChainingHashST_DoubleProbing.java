import java.util.Iterator;

public class SeparateChainingHashST_DoubleProbing<Key, Value> {
    private int M;   // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of ST objects
    private int N;  // number of keys
    
    public SeparateChainingHashST_DoubleProbing() {
        this(3); // 3 lists
    }

    public SeparateChainingHashST_DoubleProbing(int M) {
        // Create M linked lists.
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private int hash1(Key key) {
        int k = getIndex((char)key);
        return (11 * k) % M;
        
    }

    private int hash2(Key key) {
        int k = getIndex((char)key);
        return (17 * k) % M;
    }
        
    private int getIndex(char c) {
        if (Character.isUpperCase(c)) return c - 'A';
        else return c - 'a';
    }
    
    public int size() { return N; }
    
    public Value get(Key key) {
        //return (Value) st[hash(key)].get(key);
        Value v = st[hash1(key)].get(key);
        return v != null ? v : st[hash2(key)].get(key);
    }

    public void put(Key key, Value val) {

        if (val == null) {
            delete(key);
            return;
        }
        
        int h1 = hash1(key);
        int h2 = hash2(key);

        if (st[h1].get(key) != null) {
            st[h1].put(key, val);
        }
        else if (st[h2].get(key) != null) {
            st[h2].put(key, val);
        }
        else {
            int list1Size = st[h1].size();
            int list2Size = st[h2].size();
            
            if (list1Size <= list2Size) { st[h1].put(key,val); }
            else { st[h2].put(key,val); }
            N++;
        }
    }

    // Exercises 3.4.29
    public void delete(Key key) {
        int h1 = hash1(key);
        int h2 = hash2(key);
        if (st[h1].get(key) != null) {
            st[h1].delete(key);
            N--;
        }
        else if (st[h2].get(key) != null) {
            st[h2].delete(key);
            N--;
        }
    }
    
    public static void main(String[] args) {
        String s = "SEARCHXMPLSE";
        SeparateChainingHashST_DoubleProbing<Character, Integer> st
            = new SeparateChainingHashST_DoubleProbing<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            st.put(s.charAt(i),i);
        }

        st.delete('Z');
        st.delete('A');
        for (int i = 0; i < s.length(); i++) {
            StdOut.println(s.charAt(i) + " " + st.get(s.charAt(i)));
        }

        StdOut.println("Z" + " " + st.get('Z'));
        
    }
}


    

    
