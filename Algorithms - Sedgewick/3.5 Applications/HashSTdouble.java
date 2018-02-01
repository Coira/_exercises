import java.util.Iterator;

public class HashSTdouble<Value> {
    private int N = 0;
    private int M = 16;
    private Double[] keys;
    private Value[] vals;

    public HashSTdouble() {
        keys = new Double[M]; 
        vals = (Value[]) new Object[M];
    }

    public HashSTdouble(int cap) {
        keys = new Double[cap]; 
        vals = (Value[]) new Object[cap];
        M = cap;
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    
    public int hash(Double key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        HashSTdouble<Value> t = new HashSTdouble<Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null && vals[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Double key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (Double.compare(keys[i], key) == 0) { vals[i] = val; return; }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Double key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (Double.compare(keys[i], key) == 0 && vals[i] != null) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Double key) {
        if (get(key) == null) return;
        
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
        vals[position] = null;
        N--;
        
        // reinsert all elements in that cluster
        for (int i = (position+1) % M; keys[i] != null; i = (i+1) % M) {
            Double k = keys[i];
            Value v = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(k, v);
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

    public static void main(String[] args) {
        HashSTdouble<Integer> st = new HashSTdouble<Integer>();
        
        double[] d = {1.3 , 2.84 , 4.22 , 5.84 , 7.14 , 3.56 , 4.563};
        for (int i = 0; i < d.length; i++) {
            st.put(d[i], i);
        }
        StdOut.println(st.size());
        for (int i = 0; i < d.length; i++) {
            st.delete(d[i]);
        }
        StdOut.println(st.size());
    }
}
