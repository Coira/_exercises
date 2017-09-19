public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, Double[] rand) {
        double total = 0.0;
        Double[] a = new Double[rand.length]; // clone array
        for (int i = 0; i < rand.length; i++) {
            a[i] = rand[i];
        }

        total += time(alg, a);
        return total;
    }

    public static Double[] getRandomSequence(int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }
    
    public static void main(String[] args) {
        /*
        String alg1 = "UA";
        String alg2 = "OA";
        String alg3 = "ULL";
        String alg4 = "OLL";
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);

        Double[] a = getRandomSequence(N, T);
        
        double t1 = timeRandomInput(alg1, a);
        double t2 = timeRandomInput(alg2, a);
        double t3 = timeRandomInput(alg3, a);
        double t4 = timeRandomInput(alg4, a);
        
        //StdOut.printf("For %d random Doubles\n  %s is", N, alg1);
        //StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
        StdOut.printf("alg1 : " + t1 + "\n");
        StdOut.printf("alg2 : " + t2 + "\n");
        StdOut.printf("alg3 : " + t3 + "\n");
        StdOut.printf("alg4 : " + t4 + "\n");
        */
        
        PriorityQueue_UA pqua;
        PriorityQueue_OA pqoa;
        PriorityQueue_ULL pqull;
        PriorityQueue_OLL pqoll;
        Stopwatch timer;
        Double[] a;
        for (int i = 12800; i < 200000; i = i*2) {
            a = getRandomSequence(i);
            pqull = new PriorityQueue_ULL(a);
            timer = new Stopwatch();
            for (int j = 0; j < 20; j++) {
                pqull.delMax();
            }
        
            System.out.print("" + timer.elapsedTime() + " | ");
        }
            
    }
}
