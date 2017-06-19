/*
  Max-value unordered array priority queue.
  Based on the code for stacks.
*/

public class PriorityQueue_UA<Key extends Comparable<Key>> {
    private Key[] a;
    private int N = 0;

    public PriorityQueue_UA() {
        a = (Key[]) new Comparable[1];
    }

    public PriorityQueue_UA(int maxN) {
        a = (Key[]) new Comparable[maxN];
    }

    public PriorityQueue_UA(Key[] keys) {
        a = (Key[]) new Comparable[keys.length];
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i]);
        }
    }

    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    public void insert(Key key) {
        if (N == a.length) { resize(2*a.length); }
        a[N++] = key;
    }

    public void show() {
        for (int i = 0; i < N; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    
    // similar to inner loop of selection sort
    public Key max() {
        int m = 0;
        for (int i = 1; i < N; i++) {
            if (less(a[m], a[i])) m = i;
        }
        return a[m];
    }

    public Key delMax() {
        if (N > 0 && N == a.length/4) { resize(a.length/2); }
        
        Key key;
        int m = 0;
        for (int i = 1; i < N; i++) {
            if (less(a[m], a[i])) m = i;
        }
        key = a[m];

        // overwrite max value with last element in array
        N--;
        if (m != N)
            a[m] = a[N];
        
        return key;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        String[] s = "4756292".split("");
        PriorityQueue_UA<String> pq = new PriorityQueue_UA<String>(s);
        pq.show();
        pq.max();
        pq.delMax();
        pq.show();
    }
        
}
    
