public class Merge_FewerMerges {
    private static Comparable[] aux;
    
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // sort a[lo..hi]
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid); // sort left half
        sort(a, mid+1, hi); // sort right half
        if (!leq(a[mid], a[mid+1]))
            merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {

        int i = lo, j = mid+1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)                   a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );
        StdOut.println();
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // less than or equal to
    private static boolean leq(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }

    public static void main(String[] args) {        
        for (int i = 10; i <= 30; i++) {
            Integer[] a = new Integer[i];
            for (int j = 0; j < i; j++) {
                a[j] = j;
            }
            
            sort(a);
            show(a);
        }
    }
}
