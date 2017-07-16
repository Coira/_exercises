/*
  2.4.33: IndexMinPQ
  2.4.34: Added minIndex(), changeKey(), delete()
*/



public class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;
    private int[] pq;   // binary heap using 1-based indexing
    private int[] qp;   // inverse: qp[pq[i]] = pq[qp[i] = i
    private Key[] keys; // items with priorities

    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public void insert(int i, Key key) {
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    public Key minKey() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }
    
    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        return indexOfMin;
    }

    public void changeKey(int i, Key key) {
        keys[i] = key;
        sink(qp[i]);
        swim(qp[i]);
    }

    public Key keyOf(int i) {
        return keys[pq[i]];
    }
    
    public void delete(int i) {
        int index = qp[i];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;

    }
    
    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j+1, j)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean less(int i, int j) {
        Comparable a = keys[pq[i]];
        Comparable b = keys[pq[j]];
        return a.compareTo(b) < 0;
    }
    
    public static void main(String[] args) {
        String[] a = "192837465".split("");
        IndexMinPQ<String> pq = new IndexMinPQ<String>(a.length);
        for (int i = 0; i < a.length; i++) {
            pq.insert(i+1, a[i]);
        }
        
        while (!pq.isEmpty()) {
            System.out.println(pq.minKey());
            pq.delMin();
        }
    }
}


