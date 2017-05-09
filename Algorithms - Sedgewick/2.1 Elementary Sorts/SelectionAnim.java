// Selection Sort with Visualisation

public class SelectionAnim {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exhange a[i] with smallest entry in a[i+1..N).
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) { min = j; }
            }
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " " );
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static void draw(Double[] a) {
        double p = 0.01; //padding between bars
        double b = 0.004; // bar half width
        
        StdDraw.setScale(-.05, 1.05);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        for (int k = 0; k < a.length; k++) {
            StdDraw.filledRectangle((b+p)*k, a[k]/2, b, a[k]/2);
        }
        StdDraw.show();
        StdDraw.pause(100);
    }

    // animates sorting -- exercise 2.1.17
    public static void sortWithAnim(Double[] a) {
        int N = a.length;
        draw(a);
        for (int i = 0; i < N; i++) {
            // Exhange a[i] with smallest entry in a[i+1..N).
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) { min = j; }
            }
            exch(a, i, min);
            draw(a);
        }
    }
    
    public static void main(String[] args) {
        int N = 60; // number of doubles to sort
        Double[] a = new Double[N];

        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        sortWithAnim(a);
    }
}
