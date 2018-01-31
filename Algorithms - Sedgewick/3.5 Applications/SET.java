public class SET<Key extends Comparable<Key>> {
    private ST st;
    private int N = 0;
    
    public SET() {
        st = new ST();
    }

    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }

    public boolean contains(Key key) {
        return st.contains(key);
    }
    
    public void add(Key key) {
        if (!contains(key)) {
            // StdOut.println("Adding ... " + key);
            st.put(key, 1);
            N++;
        }
    }

    public void delete(Key key) {
        if (contains(key)) {
            st.delete(key);
            N--;
        }
    }

    public static void main(String[] args) {
        SET s = new SET<String>();
        String[] str = "ANROSLSQYTFD".split("");
        for (int i = 0; i < str.length; i++) {
            s.add(str[i]);
        }
        StdOut.println(s.size());
        StdOut.println(s.contains("N"));
        StdOut.println(s.contains("X"));
        for (int i = 0; i < str.length; i++) {
            s.delete(str[i]);

        }
        StdOut.println(s.size());
    }
}
