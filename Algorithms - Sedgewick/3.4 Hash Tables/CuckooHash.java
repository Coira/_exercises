import java.util.Iterator;

public class CuckooHash<Key, Value> {
    private int N = 0; // number of keys
    private int M = 4; // table size
    
    private Key[] keys1; // table 1
    private Value[] vals1;
    private Key[] keys2; // table 2
    private Value[] vals2;

    private int hash1Constant; // constant for hash 1
    private int hash2Constant; // constant for hash 2
    private int maxLoops = 10;
   
    public CuckooHash() {
        keys1 = (Key[]) new Object[M];
        vals1 = (Value[]) new Object[M];
        keys2 = (Key[]) new Object[M];
        vals2 = (Value[]) new Object[M];

        // set values for the two hash functions
        resetConstants();
    }

    public CuckooHash(int cap) {
        M = cap;
        
        keys1 = (Key[]) new Object[M];
        vals1 = (Value[]) new Object[M];
        keys2 = (Key[]) new Object[M];
        vals2 = (Value[]) new Object[M];

        // set values for the two hash functions
        resetConstants();
    }

    // usual hash from this chapter, but with added random number
    public int hash(Key key, int constant) {
        int t = key.hashCode() & 0x7fffffff;
        t += constant;
        return t % M;
    }

    private void resize(int cap) {
        CuckooHash<Key, Value> t = new CuckooHash<Key, Value>(cap);
        resetConstants();

        for (int i = 0; i < M; i++) {
            if (keys1[i] != null) {
                t.put(keys1[i], vals1[i]);
            }
            if (keys2[i] != null) {
                t.put(keys2[i], vals2[i]);
            }
        }

        keys1 = t.keys1;
        vals1 = t.vals1;
        keys2 = t.keys2;
        vals2 = t.vals2;
        M = t.M;
        
        hash1Constant = t.hash1Constant;
        hash2Constant = t.hash2Constant;
    }


    // create new hash function by adding a random number to it
    private void resetConstants() {
        hash1Constant = (int)(Math.random() * M);

        hash2Constant = hash1Constant;
        while (hash2Constant == hash1Constant) {
            hash2Constant = (int)(Math.random() * M);
        }
    }

    // replace item in keys[position] with key, and return the 
    // item we've replaced
    private Key swapKey(Key[] keys, Key key, int position) {
        Key temp = keys[position];
        keys[position] = key;
        return temp;
    }

    private Value swapValue(Value[] vals, Value val, int position) {
        Value temp = vals[position];
        vals[position] = val;
        return temp;
    }
    
    public void put(Key key, Value val) {

        // TODO eager delete
        // if (val == null) delete(key);
        
        if (get(key) != null) {
            StdOut.println("key exists");
            int h1 = hash(key, hash1Constant);
            int h2 = hash(key, hash2Constant);
            if (keys1[h1] == key) vals1[h1] = val;
            else if (keys2[h2] == key) vals2[h2] = val;
            return;
        }

        if (N >= keys1.length*2) // TODO resize when half full
            resize(M*2);
                                      
        int loops = 0;
        Key insertKey = key;  // Key to be inserted
        Value insertVal = val; // Value to be inserted
        
        while (insertKey != null) {
            Key tempKey;
            Value tempVal;

            // insert key/value pair into first table
            int h1 = hash(insertKey, hash1Constant);

            StdOut.println(insertKey + " " + loops + " " + h1);
            insertKey = swapKey(keys1, insertKey, h1);
            insertVal = swapValue(vals1, insertVal, h1);

            // insert ousted key/value pair into second table
            if (insertKey != null && insertVal != null) {
                int h2 = hash(insertKey, hash2Constant);
                insertKey = swapKey(keys2, insertKey, h2);
                insertVal = swapValue(vals2, insertVal, h2);
            }
            loops++;

            // if we've looped too many times, create new hash functions
            if (loops >= maxLoops) {
                loops = 0;
                resetConstants();
            }

            print();
            StdOut.println("\n");
        }
        N++;
    }

    public Value get(Key key) {
        int h1 = hash(key, hash1Constant);
        int h2 = hash(key, hash2Constant);

        if (keys1[h1] != null && keys1[h1] == key) return vals1[h1];
        if (keys2[h2] != null && keys2[h2] == key) return vals2[h2];

        return null;
    }

    public void delete(Key key) {
        // TODO
    }

    public void print() {
        for (int i = 0; i < keys1.length; i++) {
            Key x = keys1[i];
            if (x == null) StdOut.print("_ ");
            else StdOut.print(x + " ");
        }
        StdOut.println();
        for (int i = 0; i < keys2.length; i++) {
            Key x = keys2[i];
            if (x == null) StdOut.print("_ ");
            else StdOut.print(x + " ");
        }
    }
                                          
    public static void main(String[] args) {
        String s = "ABCDEFGHIJKLMNOP";
        CuckooHash<Character, Integer> st =
            new CuckooHash<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            st.put(s.charAt(i), i);
        }

        st.put('A', 11);
        for (int i = 0; i < s.length(); i++) {
            StdOut.println(s.charAt(i) + " " + st.get(s.charAt(i)));
        }

    }
        
}

