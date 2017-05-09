// Insertion Sort with Visualisation
import java.awt.Font;

public class InsertionTrace {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // Insert a[i] among a[i-1], a[i-2], a[i-3], ...
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
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
            StdOut.println(a[i] + " " );
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // j and i relate to the j and i indexes in sortWithAnim
    public static void draw(Double[] a,int j, int i,
                            double xPos, double yPos) {
        double p = 0.01; //padding between bars
        double b = 0.004; // bar half width
        int k;

        // left of sorting area
        StdDraw.setPenColor(StdDraw.GRAY);
        for (k = 0; k < j; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k,
                                    yPos+a[k]/2, b, a[k]/2);
        }

        // element being sorted
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(xPos+(b+p)*k,
                                yPos+a[k]/2, b, a[k]/2);
        
        // area being sorted in
        StdDraw.setPenColor(StdDraw.BLACK);
        for (k = k+1; k <= i; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k,
                                    yPos+a[k]/2, b, a[k]/2);
        }

        // right of sorting area
        StdDraw.setPenColor(StdDraw.GRAY);
        for (k=i+1; k < a.length; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k,
                                    yPos+a[k]/2, b, a[k]/2);
        }

        //StdDraw.show();
        // StdDraw.pause(1000);
    }
    
    // animates sorting -- exercise 2.1.18
    public static void sortWithAnim(Double[] a) {
        int N = a.length;

        
        StdDraw.setXscale(-.05, 1.05);
        StdDraw.setYscale(-.2, 13.5);
        StdDraw.enableDoubleBuffering();

        // values for drawing the graphs
        double h = 0.3; //padding between graphs
        double yPos = 12.5 - h;
        double xPos = 0;
        
        int i, j=0;
        draw(a, 0, 0, xPos, yPos);
        
        for (i = 1; i < N; i++) {
            for (j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }

            // increment graph positions
            if (i == 10) {
                xPos += 0.5;
                yPos = 12.5 - h;
            }
            else {
                yPos -= 1 + h;
            }
            draw(a, j, i, xPos, yPos);
        }
        StdDraw.show();
    }
    
    public static void main(String[] args) {
        int N = 20; // number of doubles to sort
        Double[] a = new Double[N];

        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        sortWithAnim(a);
    }
}
