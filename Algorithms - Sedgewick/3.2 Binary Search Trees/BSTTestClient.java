public class BSTTestClient {
    public static void main(String[] args) {
        BST<String, Integer> st;
        st = new BST<String, Integer>();

        for (int i = 0; StdIn.hasNextLine(); i++) {
            String str = StdIn.readLine();

            if (str.length() == 1) {
                st.put(str, i);
            }
            else {
                String strings[] = str.split(" ");
                if (strings[0].equals("dmax")) {
                    StdOut.println("Deleting : " + st.max());
                    
                    st.deleteMax();
                }
                else if (strings[0].equals("dmin")) {
                    StdOut.println("Deleting : " + st.min());
                    st.deleteMin();
                }
                else if (strings[0].equals("del")) {
                    if (strings.length > 1) {
                        if (st.get(strings[1]) != null) {
                            StdOut.println("Deleting : " + strings[1]);
                            st.delete(strings[1]);
                        }
                        else {
                            StdOut.println(strings[1] + " is not in the tree");
                        }
                    }
                }
                else if (strings[0].equals("floor")) {
                    if (strings.length > 1) {
                        StdOut.println(st.floor(strings[1]));
                    }
                }
                else if (strings[0].equals("ceil")) {
                    if (strings.length > 1) {
                        StdOut.println(st.ceiling(strings[1]));
                    }
                }
                else if (strings[0].equals("select")) {
                    if (strings.length > 1) {
                        int select = Integer.parseInt(strings[1]);
                        StdOut.println(st.select(select));
                    }
                }
                else if (strings[0].equals("rank")) {
                    if (strings.length > 1) {
                        
                        StdOut.println(st.rank(strings[1]));
                    }
                }
            }
            
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
