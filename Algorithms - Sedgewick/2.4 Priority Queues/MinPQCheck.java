/*
  2.4.15
  Checks the Priority Queue is min-value ordered.
*/

public class MinPQCheck {
    public static boolean check(MinPQ pq) {
        int N = pq.size();
        Comparable[] a = new Comparable[N+1];

        // move all elements from the PQ to an array
        for (int i = 1; i <= N; i++) {
            a[i] = pq.delMin();
        }

        // rebuild the PQ again -- because I can't think
        // of how to do this without destroying the PQ
        for (int i = 1; i <= N; i++) {
            pq.insert(a[i]);
        }

        // the important part, the order check
        for (int i = 1; i*2 <= N; i++) {
            if (less(a[i*2], a[i])) return false;
            if (i*2+1 <= N) {
                if (less(a[i*2+1], a[i])) return false;
            }
        }
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}



