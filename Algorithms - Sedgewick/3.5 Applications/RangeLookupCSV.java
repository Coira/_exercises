public class RangeLookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        ST<String, String> st = new ST<String, String>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);
        }

        while (!StdIn.isEmpty()) {
            String key1 = StdIn.readString();
            String key2 = StdIn.readString();
            if (st.contains(key1) && st.contains(key2)) {
                int i = st.rank(key1);
                String key = st.select(i++);
                while (!key.equals(key2)) {
                    StdOut.println(key + " " + st.get(key));
                    key = st.select(i++);
                }
                StdOut.println(key + " " + st.get(key) + "\n");
            }
        }
    }
}
                
