/* 
   2.3.17
   Eliminate Sentinels in inner while loops.
   SortCompare shows SentinelQuick to be 0.9 times as fast as Quick
*/

public class SentinelQuick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);  // Eliminate dependence on input
        int max = 0;
        for (int i = 1; i < a.length; i++)
            if (less(a[max], a[i])) max = i;
        exch(a, a.length-1, max);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1;  // left and right scan indices
        Comparable v = a[lo];  // partitioning item

        while (true) {
            // Scan right, scan left, check for scan complete, and exchange
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
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length-1; i++) {
            if (less(a[i+1], a[i])) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String[] a = "ABCD01234567".split("");
        sort(a);
        show(a);
    }
}

