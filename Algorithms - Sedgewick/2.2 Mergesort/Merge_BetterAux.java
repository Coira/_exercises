// 2.2.9: Moved aux to be local to sort()

public class Merge_BetterAux {
    //private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    private static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        // sort a[lo..hi]
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid, aux); // sort left half
        sort(a, mid+1, hi, aux); // sort right half
        merge(a, lo, mid, hi, aux);
    }

    public static void merge(Comparable[] a, int lo, int mid,
                             int hi, Comparable[] aux) {
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

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );
        StdOut.println();
    }

    public static void main(String[] args) {
        String a[] = "MERGESORTEDEXAMPLE".split("");
        sort(a);
        show(a);
    }
}
