public class ImprovedMerge {
    //private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        //aux = new Comparable[a.length];
        sort(aux, 0, a.length - 1, a);
    }
    
    private static void sort(Comparable[] a, int lo, int hi,
                             Comparable[] aux) {
        if (hi <= lo) return;
        if (a.length < 15) {
            // use insertion sort for small subarrays, 10-15% faster
            insertionSort(a);
        }
        else {
            int mid = lo + (hi - lo)/2;
            sort(aux, lo, mid, a); // sort left half
            sort(aux, mid+1, hi, a); // sort right half
            if (!leq(a[mid],a[mid+1])) { // skip merge, already sorted
                merge(aux, lo, mid, hi, a);
            }
            else {
                // we still need to do *some* copying between arrays
                for (int i = lo; i < hi-lo+1; i++)
                    aux[i] = a[i];
            }
        }
    }
    
    public static void merge(Comparable[] a, int lo, int mid, int hi,
                             Comparable[] aux) {

        int i = lo, j = mid+1;
 
        //for (int k = lo; k <= hi; k++)
        //    aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)                   a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    
    private static void insertionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
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


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean leq(Comparable v, Comparable w) {
        // less than or equal to
        return v.compareTo(w) <= 0;
    }
    
    public static void main(String[] args) {
        String[] a = "MERGESORTEXAMPLE".split("");
        sort(a);
        show(a);
    }
}
