/* 
   Client for Symbol Table
   Counts the frequency of occurence of the strings in standard input
*/

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);  // key-length cutoff
        String file = args[1];
        OrderedSequentialSearchST<String, Integer> st =
            new OrderedSequentialSearchST<String, Integer>();
        In text = new In(file);
        
        while (!text.isEmpty()) {
            // Build symbol table and count frequencies.
            String[] words = text.readLine().split(" ");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (word.length() < minlen) continue;  // Ignore short keys
                if (!st.contains(word)) st.put(word, 1);
                else                    st.put(word, st.get(word) + 1);
            }
        }

        //for (String word : st.keys()) {
        //    StdOut.println(word + " " + st.get(word));
        //}
        // Find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) >= st.get(max)) {
                StdOut.println(word + " " + st.get(word));
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
        
        
    }
}

            
