/* There doesn't seem to be much improvement using Insertion Sort for 
   subarrays of size <= 15 */

public class InsertQuick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);  // Eliminate dependence on input
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        if (hi - lo <= 28) {
            insertSort(a, lo, hi);
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static void insertSort(Comparable[] a, int lo, int hi) {
        int N = a.length;
        for (int i = lo+1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); --j) {
                exch(a, j-1, j);
            }
        }
    }
    
    private static int partition(Comparable[] a, int lo, int hi) {
        // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1;  // left and right scan indices
        Comparable v = a[lo];  // partitioning item

        while (true) {
            // Scan right, scan left, check for scan complete, and exchange
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
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
	
    public static void main(String[] args) {
        String[] a = "ABCD01234567".split("");
        sort(a);
        show(a);
    }


}

