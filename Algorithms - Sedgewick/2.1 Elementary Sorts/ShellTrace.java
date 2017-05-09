// Shellsort with Visualisation
import java.awt.Font;

public class ShellTrace {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) { h = 3*h + 1; }
        while (h >= 1) {
            //h-sort the array
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*n], a[i-3*h],...
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
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

    public static void draw(Double[] a, String text, double xPos, double yPos) {
        double p = 0.01; //padding between bars
        double b = 0.004; // bar half width


        //StdDraw.clear();
        StdDraw.textLeft(xPos, yPos+1.1, text);
        for (int k = 0; k < a.length; k++) {
            StdDraw.filledRectangle(xPos+(b+p)*k, yPos+a[k]/2, b, a[k]/2);
        }
        StdDraw.show();
        //StdDraw.pause(200);
    }
    
    // animates sorting -- exercise 2.1.17
    public static void sortWithAnim(Double[] a) {
        StdDraw.setXscale(-.05, 1.05);
        StdDraw.setYscale(-.2, 9.25);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setFont(new Font("Arial", Font.BOLD, 14));
                        
        int N = a.length;
        int h = 1;

        // vales for drawing the graphs
        double p = 0.8; // padding between graphs
        double yPos = 7.5;
        double xPos = 0;

        // draw unsorted array
        draw(a, "input", xPos, yPos);
        
        while (h < N/3) { h = 3*h + 1; }
        while (h >= 1) {
            //h-sort the array
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*n], a[i-3*h],...
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                    //draw(a);
                }
            }
            yPos -= (1 + p);
            String text = h == 1 ? "result" : h+"-sorted";
            draw(a, text, xPos, yPos);
            h = h/3;
        }

        // draw result
        //draw(a, "result", xPos, yPos);
    }
    
    public static void main(String[] args) {
        int N = 70; // number of doubles to sort
        Double[] a = new Double[N];

        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        sortWithAnim(a);

    }
}
                

