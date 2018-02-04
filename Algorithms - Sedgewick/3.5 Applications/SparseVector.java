public class SparseVector
{
    private STdouble<Double> st;
    public SparseVector() {
        st = new STdouble<Double>();
    }
    
    public int size() {
        return st.size();
    }
    
    public void put(double i, double x) {
        st.put(i, x);
    }
    
    public double get(double i) {
        if (!st.contains(i)) return 0.0;
        else return st.get(i);
    }
    
    public double dot(double[] that) {
        double sum = 0.0;
        for (double i : st.keys())
            sum += that[(int)i]*this.get(i);
        return sum;
    }

    public static void main(String[] args) {
        SparseVector sp = new SparseVector();
        sp.put(0, 1.3);
        sp.put(1, 4.5);
        sp.put(2, 49.34);
        StdOut.println(sp.get(2));
        double[] d = {5.83, 57.38, 68.9};
        StdOut.println(sp.dot(d));
    }
}
