import java.util.Iterator;

public class CuckooHash<Key, Value> {
    private int N = 0; // number of keys
    private int M = 1; // table size
    
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
        hash1Constant = (int)(Math.random() * (M * 10) + 2);

        hash2Constant = hash1Constant;
        while (hash2Constant == hash1Constant) {
            hash2Constant = (int)(Math.random() * (M * 10) + 2);
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

        // no null keys
        if (key == null) return;
        
        // eager delete, if val is null, remove item and return
        if (val == null) {
            delete(key);
            return;
        }

        // if key exists, replace value and return
        if (get(key) != null) {
            int h1 = hash(key, hash1Constant);
            int h2 = hash(key, hash2Constant);
            if (keys1[h1] == key) vals1[h1] = val;
            else if (keys2[h2] == key) vals2[h2] = val;
            return;
        }

        if (N >= keys1.length) 
            resize(M*2);
                                      
        int loops = 0;
        Key insertKey = key;  // Key to be inserted
        Value insertVal = val; // Value to be inserted

        // loop is terminated when we have no more keys to insert
        while (insertKey != null) {
            Key tempKey;
            Value tempVal;

            // insert key/value pair into first table
            int h1 = hash(insertKey, hash1Constant);

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
        }
        N++;
    }

    public Value get(Key key) {
        int h1 = hash(key, hash1Constant);
        int h2 = hash(key, hash2Constant);

        if (keys1[h1] == key) return vals1[h1];
        if (keys2[h2] == key) return vals2[h2];

        return null;
    }

    public void delete(Key key) {
        int h1 = hash(key, hash1Constant);
        int h2 = hash(key, hash2Constant);

        if (keys1[h1] == key) {
            keys1[h1] = null;
            vals1[h1] = null;
            N--;
        }
        else if (keys2[h2] == key) {
            keys2[h2] = null;
            vals2[h2] = null;
            N--;
        }

        if (N > 0 && N <= keys1.length/2) { resize(keys1.length/2); }
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
        StdOut.println();
    }

    public Iterable<Key> keys() {
        return new CuckooHashIterable();
    }

    private class CuckooHashIterable implements Iterable<Key> {
        public Iterator<Key> iterator() {
            return new Iterator<Key>() {
                private int i = 0;
                private Key[] keys = keys1;
                private int count = 0;
                public boolean hasNext() { return count < N; }
                public Key next() {
                    if (i == keys.length) {
                        keys = keys2;
                        i = 0;
                    }
                    if (keys[i] == null) {
                        i++;
                        return next();
                    }
                    count++;
                    return keys[i++];
                }
                public void remove() { }
            };
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
        st.delete('B');
        st.print();
        StdOut.println();
        
        Iterable<Character> it = st.keys();
        for (Character c : it) {
            StdOut.print(c + ":" + st.get(c) + " ");
        }
        StdOut.println();

        for (int i = 0; i < s.length()-1; i++) {
            st.delete(s.charAt(i));
        }
        st.delete('X');
        it = st.keys();
        for (Character c : it) {
            StdOut.print(c + ":" + st.get(c) + " ");
        }
    }
        
}

