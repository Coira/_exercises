import java.util.Iterator;

public class HashSETint {
    private int N = 0;
    private int M = 16;
    private Integer[] keys;

    public HashSETint() {
        keys = new Integer[M]; 
    }

    public HashSETint(int cap) {
        keys = new Integer[cap]; 
        M = cap;
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    
    public int hash(Integer key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        HashSETint t = new HashSETint(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i]);
            }
        }
        keys = t.keys;
        M = t.M;
    }
    
    public void put(Integer key) {
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (Integer.compare(keys[i], key) == 0) { return; }
        }
        keys[i] = key;
        N++;
    }

    public void delete(Integer key) {
        if (N > 0 && N <= M/8) {
            resize(M/4);
        }
        int position = 0;
        // find position of key to be deleted
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (Integer.compare(keys[i], key) == 0) position = i;
        }

        // delete keys
        keys[position] = null;
        N--;
        
        // reinsert all elements in that cluster
        for (int i = (position+1) % M; keys[i] != null; i = (i+1) % M) {
            Integer k = keys[i];
            keys[i] = null;
            N--;
            put(k);
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
}
