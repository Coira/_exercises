import java.util.Iterator;

public class HashSETdouble {
    private int N = 0;
    private int M = 16;
    private Double[] keys;

    public HashSETdouble() {
        keys = new Double[M]; 
    }

    public HashSETdouble(int cap) {
        keys = new Double[cap]; 
        M = cap;
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    
    public int hash(Double key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        HashSETdouble t = new HashSETdouble(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i]);
            }
        }
        keys = t.keys;
        M = t.M;
    }
    
    public void put(Double key) {
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (Double.compare(keys[i], key) == 0) { return; }
        }
        keys[i] = key;
        N++;
    }

    public void delete(Double key) {
        if (N > 0 && N <= M/8) {
            resize(M/4);
        }
        int position = 0;
        // find position of key to be deleted
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (Double.compare(keys[i], key) == 0) position = i;
        }

        // delete keys
        keys[position] = null;
        N--;
        
        // reinsert all elements in that cluster
        for (int i = (position+1) % M; keys[i] != null; i = (i+1) % M) {
            Double k = keys[i];
            keys[i] = null;
            N--;
            put(k);
        }
    }
                
    // Exercise 3.4.19
    public Iterable<Double> keys() {
        return new HashTableIterable();
    }

    private class HashTableIterable implements Iterable<Double> {
        public Iterator<Double> iterator() {
            return new Iterator<Double>() {
                private int i = 0;
                private int count = 0;
                public boolean hasNext() { return count < N; }
                public Double next() {
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
