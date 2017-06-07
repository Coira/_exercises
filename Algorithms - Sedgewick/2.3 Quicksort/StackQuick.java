/* 
   2.3.20
   Nonrecursive quicksort -- uses a stack.

   Seems to be about 90% slower than Quick using Deque, and 50% slower using Stack.
   Speed dependent on efficiency of stack structure.

*/

import java.util.Deque;
import java.util.ArrayDeque;

public class StackQuick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        //Stack<Integer> stack = new Stack<Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        stack.push(a.length-1);
        
        int lo, hi, j;
        while (!stack.isEmpty()) {
            hi = stack.pop();
            lo = stack.pop();
            if (hi <= lo) {
                continue;
            }
            j = partition(a, lo, hi);
            stack.push(lo);
            stack.push(j);
            stack.push(j+1);
            stack.push(hi);
        }
    }

    	
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

        
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + "");
        StdOut.println();
    }

    public static void show(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            StdOut.print(a[i] + "");
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

    public static boolean isSorted(Comparable a[]) {
        for (int i = 0; i < a.length-1; i++) {
            if (less(a[i+1], a[i])) { return false; }
        }
        return true;
    }
    
    public static void main(String[] args) {
    }
}
