/*
  Max value heap priority queue.
*/

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // pq[0] left unused
    private int N = 0;

    public MaxPQ() {
        pq = (Key[]) new Comparable[2];
    }
    
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }

    /* 2.4.19 */
    public MaxPQ(Key[] a) {
        N = a.length;
        pq = (Key[]) new Comparable[N+1];
        
        for (int i = N; i > N/2; i--) {
            pq[i] = a[i-1];
        }
        for (int i = N/2; i >= 1; i--) {
            pq[i] = a[i-1];
            sink(i);
        }

    }

    /* 2.4.22 */
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (N == pq.length-1) { resize(1+2*pq.length); }
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        if (N > 0 && N == (pq.length/4)) { resize(pq.length/2); }
        Key max = pq[1];     // Retrieve max key from top.
        exch(1, N--);        // Exch with last item.
        pq[N+1] = null;      // Avoid loitering.
        sink(1);             // Restore heap property.
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void show() {
        for (int i = 1; i <= N; i++) {
            StdOut.print(pq[i] + " ");
        }
        StdOut.println();
    }
                 
    public static void main(String[] args) {
        String[] a = "123456789".split("");
        MaxPQ<String> pq = new MaxPQ<String>();
        for (int i = 0; i < a.length; i++) {
            pq.insert(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            pq.delMax();
        }
        //pq.show();
    }

}



    
    
