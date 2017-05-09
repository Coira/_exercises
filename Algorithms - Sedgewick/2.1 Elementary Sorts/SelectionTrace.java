// Selection Sort with Visualisation
import java.util.ArrayList;

public class SelectionTrace {
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
            StdOut.println(a[i] + " " );
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // i and min correspond to the values i and min in sortWithAnim
    public static void draw(Double[] a, int i, int min,
                            double xPos, double yPos) {

        double p = 0.01; //padding between bars
        double b = 0.004; // bar half width

        int k = 0;
        // sorted area
        StdDraw.setPenColor(StdDraw.GRAY);
                
        for (k = 0; k < i; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k,
                                    yPos+a[k]/2, b, a[k]/2);
        }
            
        // unsorted area left of element we're sorting
        StdDraw.setPenColor(StdDraw.BLACK);
        for (k = i; k < min; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k,
                                    yPos+a[k]/2, b, a[k]/2);
        }
        k = min;
        
        // element we're sorting
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(xPos + (b+p)*k,
                                yPos+a[k]/2, b, a[k]/2);

        // unsorted area right of element we're sorting
        StdDraw.setPenColor(StdDraw.BLACK);
        for (k = min+1; k < a.length; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k,
                                    yPos+a[k]/2, b, a[k]/2);
        }
    }

    // animates sorting -- exercise 2.1.18
    public static void sortWithAnim(Double[] a) {
        
        StdDraw.setXscale(-.05, 1.05);
        StdDraw.setYscale(-.2, 13.5);
        StdDraw.enableDoubleBuffering();

        // values for drawing the graphs
        double h = 0.3; //padding between graphs
        double yPos = 12.5 - h;
        double xPos = 0;
        
        int N = a.length;
  
        for (int i = 0; i < N; i++) {
            // Exhange a[i] with smallest entry in a[i+1..N).
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) { min = j; }
            }

            draw(a, i, min, xPos, yPos);
            exch(a, i, min);

            // move position of the graph down and maybe right
            if (i == 9) {
                xPos += 0.5;
                yPos = 12.5 - h;
            }
            else {
                yPos -= 1 + h;
            }        
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
