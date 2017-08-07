/* 3.1.1 */

public class GradesTable {
    private String[] grades;
    private double[] points;
    private int N = 11;

    public GradesTable() {
        grades = "A+,A,A-,B+,B,B-,C+,C,C-,D,F".split(",");
        points = new double[] {4.33,4.00,3.67,3.33,3.00,2.67, 
                               2.33,2.00,1.67,1.00,0.00};
    }

    public double getPoints(String grade) {
        // slow, but there are only 11 items in the array
        for (int i = 0; i < N; i++) {
            if (grades[i].equals(grade)) {
                return points[i];
            }
        }
        return 0.00; // or throw error
    }

    public double GPA(String[] grades) {
        double count = 0.00;
        for (int i = 0; i < grades.length; i++) {
            count += getPoints(grades[i]);
        }
        return count/grades.length;
    }
        
    public static void main(String[] args) {
        GradesTable gt = new GradesTable();
        String str = "";
        while (!StdIn.isEmpty()) {
            str += StdIn.readString() + ",";
        }
        StdOut.println(gt.GPA(str.split(",")));
    }
}

            
        
