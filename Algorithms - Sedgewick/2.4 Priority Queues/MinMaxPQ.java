/*
  2.4.29: Priority Queue that supports min and max operations.
*/

public class MinMaxPQ<Key extends Comparable<Key>> {
    private Key[] maxHeap;
    private Key[] minHeap;
    private int maxN = 0;  // size of max heap
    private int minN = 0;  // size of min heap
    private int N = 0;     // actual number of items
    
    public MinMaxPQ(int sz) {
        maxHeap = (Key[]) new Comparable[sz+1];
        minHeap = (Key[]) new Comparable[sz+1];
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }

    public void insert(Key v) {
        maxN++;
        minN++;
        N++;
        maxHeap[maxN] = v;
        minHeap[minN] = v;
        swim(maxN, minN);
    }

    public Key delMax() {
        if (isEmpty()) { return null; }
        
        Key max = maxHeap[1];
        exch(maxHeap, 1, maxN--);
        maxHeap[maxN+1] = null;
        sinkMax(1);
        
        N--;
        if (N == 0) {
            minN = maxN = 0;
        }
        
        return max;
    }

    public Key delMin() {
        if (isEmpty()) { return null; }
        
        Key min = minHeap[1];
        exch(minHeap, 1, minN--);
        minHeap[minN+1] = null;
        sinkMin(1);

        N--;
        if (N == 0) {
            minN = maxN = 0;
        }
        
        return min;
    }
    
    private void exch(Key[] pq, int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int maxN, int minN) {
        int k = maxN;
        while (k > 1 && less(maxHeap, k/2, k)) {
            exch(maxHeap, k/2, k);
            k = k/2;
        }

        k = minN;
        while (k > 1 && less(minHeap, k, k/2)) {
            exch(minHeap, k/2, k);
            k = k/2;
        }
    }
    
    private void sinkMax(int k) {
        while (2*k <= maxN) {
            int j = 2*k;
            if (j < maxN && less(maxHeap, j, j+1)) j++;
            if (!less(maxHeap, k, j)) break;
            exch(maxHeap, k, j);
            k = j;
        }
    }
    
    private void sinkMin(int k) {
        while (2*k <= minN) {
            int j = 2*k;
            if (j < minN && less(minHeap, j+1, j)) j++;
            if (!less(minHeap, j, k)) break;
            exch(minHeap, k, j);
            k = j;
        }
    }    

    private boolean less(Key[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    // does not accurately represent a minmax heap, for testing only
    public void show() {
        int maxBound = Math.min(N, maxN);
        StdOut.print("Max Heap: ");
        for (int i = 1; i <= maxBound; i++) {
            StdOut.print(maxHeap[i]);
        }
        int minBound = Math.min(N, minN);
        StdOut.print("\nMin Heap: ");
        for (int i = 1; i <= minBound; i++) {
            StdOut.print(minHeap[i]);
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        String[] a = "1234598760".split("");
        MinMaxPQ<String> pq = new MinMaxPQ<String>(a.length);
        for (int i = 0; i < a.length; i++) {
            pq.insert(a[i]);
        }
        pq.show();
        while (!pq.isEmpty()) {
            StdOut.println("Max: " + pq.delMax());
            StdOut.println("Min: " + pq.delMin());
        }

        for (int i = 0; i < 4; i++) {
            pq.insert(a[i]);
        }
        pq.show();
        pq.delMin();
        pq.delMin();
        pq.delMin();
        pq.show();
        System.out.println(pq.delMax());
        pq.show();
        
    }
}
    
                
