public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Sentinel")) { SentinelQuick.sort(a); }
        if (alg.equals("Quick")) { Quick.sort(a); }
        if (alg.equals("M3Quick")) { M3Quick.sort(a); }
        if (alg.equals("Stack")) { StackQuick.sort(a); }
        if (alg.equals("Insert")) { InsertQuick.sort(a); }
        if (alg.equals("M3Insert")) { M3InsertQuick.sort(a); }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // Use alg to sort T random arrays of length N
        double total = 0.0;
        Double[] a = new Double[N];
        // Integer[] a = new Integer[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                //a[i] = (double)(i);
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles\n  %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
        StdOut.printf("alg1 : " + t1 + "\n");
        StdOut.printf("alg2 : " + t2 + "\n");
    }
}
