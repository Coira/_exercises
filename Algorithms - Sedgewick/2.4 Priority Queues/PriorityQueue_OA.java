/*
  Max-value ordered array priority queue.
  Based on the code for stack.
*/

public class PriorityQueue_OA<Key extends Comparable<Key>>{
                 
    private Key[] a;
    private int N = 0;

    public PriorityQueue_OA() {
        a = (Key[]) new Comparable[1];
    }
    
    public PriorityQueue_OA(int maxN) {
        a = (Key[]) new Comparable[maxN];
    }

    public PriorityQueue_OA(Key[] keys) {
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
        if (N == a.length) { resize(a.length*2); }

        // put key in the last position and move it left until
        // it reaches the correct position

        a[N++] = key;
        for (int i = N-1; i > 0 && less(a[i], a[i-1]); i--) {
            exch(i, i-1);
        }
    }

    private void exch(int i, int j) {
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
        
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public Key max() {
        return a[N-1];
    }

    public Key delMax() {
        return a[--N];
    }
    
        
    public void show() {
        for (int i = 0; i < N; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] s = "94821691".split("");
        PriorityQueue_OA<String> pq = new PriorityQueue_OA<String>();
        for (int i = 0; i < s.length; i++) {
            pq.insert(s[i]);
        }
        pq.show();
    }
    
}
