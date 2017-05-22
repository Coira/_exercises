public class NaturalMerge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
 
        int i = 0;
        int j = i;
        int c = 0; // number of iterations
        while (true) {  // end when first sorted subarray spans whole array
            c++;

            // find the first sorted subarray
            for (j = i+1; j < N && leq(a[j-1], a[j]); j++) {
                ;
            }

            int n;
            // find second array starting at end of first array
            for (n = j+1; n < N && leq(a[n-1], a[n]); n++) {
                ;
            }

            // first subarray spans whole array, sorted
            if (j == N) { break; }
            
            merge(a, i, j, n-1);
        }
    }

    public static void merge(Comparable[] a, int  lo, int mid, int hi) {
        int i = lo, j = mid;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid)                   a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    // less than or equal to
    public static boolean leq(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );
        StdOut.println();
    }
    
    public static void main(String[] args) {
        String[] a = "NATURALMERGESORTEXAMPLE".split("");
        sort(a);
        show(a);
        a = "ZYXWVUTS".split("");
        sort(a);
        show(a);
    }
}
