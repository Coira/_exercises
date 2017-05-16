// Prints the number of array accesses for N=1 to N=512

public class Merge_AA {
    private static Comparable[] aux;
    private static int count = 0;

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
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int  lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            count += 2;
        }
        
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
                count += 2;
            }
            else if (j > hi) {
                a[k] = aux[i++];
                count += 2;
            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += 4;
            }
            else {
                a[k] = aux[i++];
                count += 2;
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        System.out.println("Ex: 2.2.6 -- Number of array accesses used by " +
                           "top-down merge\n" +
                           "Output from Merge_AA\n");
        System.out.format("%-10s%-10s%-10s%n", "Length", "Accesses", "Expected");
        for (int i = 1; i <= 512; i++) {
            Integer[] a = new Integer[i];
            for (int j = 0; j < i; j++) {
                a[j] = 512-j;
            }
            count = 0;
            sort(a);
            double ex = 6 * a.length * Math.log(a.length)/Math.log(2);
            System.out.format("%-10d%-10d%-10.2f%n", i, count, ex);
            
        }
    }
    
}
