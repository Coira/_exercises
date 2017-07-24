/* 2.5.12 Scheduling */

// A modified Min-value priority queue

public class SPT<Key, Value extends Comparable<Value>> {

    private Key[] keys;
    private Value[] values;
    private int N = 0;

    public SPT() {
        keys = (Key[]) new Object[2];
        values = (Value[]) new Comparable[2];
        keys[0] = null;
        values[0] = null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Key[] tempK = (Key[]) new Object[max];
        Value[] tempV = (Value[]) new Comparable[max];

        for (int i = 0; i <= N; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }

        keys = tempK;
        values = tempV;
    }

    public void insert(Key k, Value v) {
        N++;
        if (N+1 == keys.length) { resize(keys.length*2); }
        
        keys[N] = k;
        values[N] = v;
        swim(N);
    }

    public String delMin() {
        if (N > 0 && N+1 == keys.length/4) { resize(keys.length/2); }
        Key minK = keys[1];
        Value minV = values[1];
        exch(1, N--);
        keys[N+1] = null;
        values[N+1] = null;
        sink(1);
        return minK + " " + minV;
    }
    
    private void swim(int n) {
        while (n > 1 && less(n, n/2)) {
            exch(n/2, n);
            n = n/2;
        }
    }

    private void sink(int n) {
        while (2*n < N) {
            int j = 2*n;
            if (j < N && less(j+1, j)) j++;
            if (!less(j, n)) break;
            exch(n, j);
            n = j;
        }
    }
    
    private boolean less(int i, int j) {
        return values[i].compareTo(values[j]) < 0;
    }

    private void exch(int i, int j) {
        Key k = keys[i];
        keys[i] = keys[j];
        keys[j] = k;

        Value v = values[i];
        values[i] = values[j];
        values[j] = v;
    }

    public void show() {
        for (int i = 1; i <= N; i++) {
            StdOut.println(keys[i] + ": " + values[i]);
        }
        StdOut.println();
    }

    public static void main (String[] args) {
        String[] str = new String[2];
        SPT spt = new SPT();
        
        while (!StdIn.isEmpty()) {
            str = (StdIn.readLine()).split(" ");
            spt.insert(str[0], str[1]);
        }
        spt.show();
        StdOut.println(spt.delMin());
        StdOut.println(spt.size());
    }
}

        
    
