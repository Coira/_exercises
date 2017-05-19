public class SublinearMerge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        if (a.length % 5 != 0) { return; }
        int M = 5;
        //aux = new Comparable[Math.max(M, a.length/M)];
        aux = new Comparable[M];  // why max(M, N/M) ?
        sort(a, M);
    }
        
    private static void sort(Comparable[] a, int M) {
        for (int i = 0; i < a.length; i += M) {
            selectionSort(a, i, i+M);
        }
        for (int k = 0; k < a.length/M; k++) {
            for (int i = 0; i < a.length-M; i+= M) {
                merge(a, i, i+M, M);
            }
        }
    }

    // b1 = start of block 1
    // b2 = start of block 2
    // M = block size
    public static void merge(Comparable[] a, int b1, int b2, int M) {
        int k;
        for (k = 0; k < M; k++)
            aux[k] = a[b1+k];
        k = 0;
        int i = b1, j = b2;
        // copy either aux or second block to first
        while(i < j && k < M && j < b2+M) {
            if (less(aux[k], a[j])) { a[i++] = aux[k++]; }
            else { a[i++] = a[j++]; }
        }
        // if anything left in aux, copy over to a
        if (k < M) {
            for (int x = k; x < M; x++) {
                a[i++] = aux[x];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );
        StdOut.println();
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) { return false; }
        }
        return true;
    }
    
    private static void selectionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            // exchange a[i] with the smallest entry in a[i+1...N)
            int min = i;
            for (int j = i+1; j < hi; j++) {
                if (less(a[j], a[min])) { min = j; }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Double[] a;
        int N = 1000;
        int M = 5;
        a = new Double[N];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            sort(a);
            assert(isSorted(a));
        }
    }
}
