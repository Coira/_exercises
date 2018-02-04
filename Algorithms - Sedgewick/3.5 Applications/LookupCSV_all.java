public class LookupCSV_all {
    public static void main(String[] args) {
        In in = new In(args[0]);
        // first field is the key, everything atfer that are values
        // int keyField = Integer.parseInt(args[1]);
        // int valField = Integer.parseInt(args[2]);

        ST<String, String[]> st = new ST<String, String[]>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[0];
            String[] values = new String[tokens.length-1];
            for (int i = 1; i < tokens.length; i++) {
                values[i-1] = tokens[i];
            }
                                        
            st.put(key, values);
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                String[] values = st.get(query);
                for (int i = 0; i < values.length; i++) {
                    StdOut.print(values[i] + " ");
                }
                StdOut.println();
            }
        }
    }
}
                
