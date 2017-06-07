import java.util.Stack;

public class StackQuick {
    public static void sort(Comparable[] a) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        stack.push(a.length-1);
    }

    	
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

        
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
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

    private static void exch(Comparable a[], int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static void main(String[] args) {
        String[] a = "ABCD01234567".split("");
        sort(a);
        show(a);
    }
}
