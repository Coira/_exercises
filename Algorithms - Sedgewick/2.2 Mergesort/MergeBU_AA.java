// Computes array access used by bottom up merge

public class MergeBU_AA {
    private static Comparable[] aux;
    private static int count = 0;
    
    public static void sort(Comparable[] a) {
        // Do lgN passes of pairwise merges
        int N = a.length;
        aux = new Comparable[N];
        
        for (int sz = 1; sz < N; sz = sz+sz) {
            // sz: subarray
            // lo: subarray index
            for (int lo = 0; lo < N-sz; lo += sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
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

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );
        StdOut.println();
    }

    public static void main(String[] args) {
        System.out.println("Ex: 2.2.6 -- Number of array accesses used by " +
                           "bottom-up merge\n" +
                           "Output from MergeBU_AA\n");
        System.out.format("%-10s%-10s%-10s%n", "Length", "Accesses", "Expected");
        for (int i = 1 ; i <= 512; i++) {
            Integer[] a = new Integer[i];
            for (int j = 0; j < i; j++) {
                a[j] = 512-j;
            }
            count = 0;            
            sort(a);
            double ex = 6 * i * Math.log(i)/Math.log(2);
            System.out.format("%-10d%-10d%-10.2f%n", i, count, ex);
            
            }
    }
    
        
        
}
                    
