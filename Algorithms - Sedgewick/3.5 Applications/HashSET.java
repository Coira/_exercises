/*
SET() create an empty set
void add(Key key) add key into the set
void delete(Key key) remove key from the set
boolean contains(Key key) is key in the set?
boolean isEmpty() is the set empty?
int size() number of keys in the set
String toString() string representation of the set
*/

public class HashSET<Key> {
    private HashST<Key, Integer> hst;
    private int N = 0;

    public HashSET() {
        hst = new HashST<Key, Integer>();
    }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    public void add(Key key) {
        if (!contains(key)) {
            hst.put(key, 1);
            N++;
        }
    }

    public void delete(Key key) {
        if (contains(key)) {
            hst.delete(key);
            N--;
        }
    }

    public boolean contains(Key key) {
        return hst.get(key) != null;
    }

    
    public static void main(String[] args) {
        String[] str = "AWRFSPTGDS".split("");
        HashSET<String> st = new HashSET<String>();
        
        for (int i = 0; i < str.length; i++)
            st.add(str[i]);

        StdOut.println(st.size());

        for (int i = 0; i < str.length; i++)
            st.delete(str[i]);

        StdOut.println(st.size());
    }
}
