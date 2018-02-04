import java.util.Iterator;

public class Invert {
    public static ST<String, Bag<String>> invert(ST<String, Bag<String>> st) {
        ST<String, Bag<String>> ts = new ST<String, Bag<String>>();
        for (String key : st.keys()) {

            // all elements in bag gets turned into keys
            Bag<String> bag = st.get(key); 
            Iterator<String> it = bag.iterator();
            
            // add all strings from bag to the new ST as keys
            // if the key already exists, update the key's value (bag)
            while (it.hasNext()) {
                String tsKey = it.next();
                Bag<String> tsBag;
                if (ts.contains(tsKey)) {
                    tsBag = ts.get(tsKey);
                }
                else {
                    tsBag = new Bag<String>();
                }
                tsBag.add(key);
                ts.put(tsKey, tsBag);
            }
        }
        return ts;
    }

    public static void show(ST<String, Bag<String>> st) {
        for (String key : st.keys()) {
            Bag<String> bag = st.get(key);
            Iterator<String> it = bag.iterator();
            StdOut.print(key + ": ");
            while (it.hasNext()) {
                StdOut.print(it.next() + " ");
            }
            StdOut.println();
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        ST<String, Bag<String>> st = new ST<String, Bag<String>>();
        String[] keys = "ABCDEFGHIJKLM".split("");
        String[] vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

        for (int i = 0; i < 10; i++) {
            Bag<String> bag = new Bag<String>();
            for (int j = 0; j < 4; j++) {
                int c = (int)(Math.random() * (vals.length-1));
                bag.add(vals[c]);
            }
            int k = (int)(Math.random() * (keys.length-1));
            st.put(keys[k], bag);
        }

        show(st);
        st = invert(st);
        show(st);
        
    }
}
