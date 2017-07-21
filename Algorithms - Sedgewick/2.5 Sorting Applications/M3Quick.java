/* 
   2.3.18 Median-of-3 partioning
   Use a pivot that is the median of 3 items

   About 1.4 times faster than original Quick, as there is no need to
   shuffle the array anymore.

*/

public class M3Quick {
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a);  // Eliminate dependence on input
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); 
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;  // left and right scan indices
        int mid = lo+(hi-lo)/2; 
        Comparable v;// = a[lo];

        // find median of a[lo], a[mid], a[hi]
        if (less(a[hi], a[mid])) { exch(a, mid, hi); }
        if (less(a[mid], a[lo])) { exch(a, lo, mid); }
        if (less(a[hi], a[mid])) { exch(a, mid, hi); }
        exch(a, lo, mid); // put pivot into first position -- acts as sentinel
        v = a[lo];

        while (true) {
            // Scan right, scan left, check for scan complete, and exchange;
            while (less(a[++i], v)) 
                ;
            while (less(v, a[--j]))
                ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
	
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + "");
        StdOut.println();
    }

    public static void show(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            StdOut.print(a[i]);
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length-1; i++) {
            if (less(a[i+1], a[i])) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String[] a = "012345768".split("");
            sort(a);
            assert(!isSorted(a));
        }
    }


}

