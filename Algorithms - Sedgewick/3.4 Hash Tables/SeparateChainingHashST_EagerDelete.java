public class SeparateChainingHashST_EagerDelete<Key, Value> {
    
    public class Node{
        Key key;
        Value val;
        Node next;
        public Node() { }
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    private int M;   // hash table size
    private Node first; // start of hash table
    
    //private SequentialSearchST<Key, Value>[] st;  // array of ST objects
    private Node[] hashTable;
    
    public SeparateChainingHashST_EagerDelete() {
        //this(997); // 997 lists
        this(5);
    }

    public SeparateChainingHashST_EagerDelete(int M) {
        // Create M linked lists.
        this.M = M;
        hashTable = new SeparateChainingHashST_EagerDelete.Node[M];
    }
    
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        //return (Value) st[hash(key)].get(key);
        int h = hash(key);
        Node node = hashTable[h];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.val;
            }
            node = node.next;
        }
        return null;
    }

    public void put(Key key, Value val) {
        int h = hash(key);
        Node first = hashTable[h];
        for (Node n = first; n != null; n = n.next) {
            if (key.equals(n.key)) {
                n.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        hashTable[h] = first;
    }

    /* Ex. 3.4.9 */
    public void delete(Key key) {
        int h = hash(key);

        Node first = new Node();
        first.next = hashTable[h];
        Node curr = hashTable[h];

        // check if key is the first node in the bucket
        if (curr != null && curr.key.equals(key)) {
            hashTable[h] = curr.next;
            return;
        }

        // otherwise look through the rest of the list
        while (curr != null && curr.next != null) {
            if (curr.next.key.equals(key)) {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }        
        
    }


    public void show() {
        for (int i = 0; i < M; i++) {
            Node curr = hashTable[i];
   
            if (curr != null) {
                StdOut.print(i + ": ");
                while (curr != null) {
                    StdOut.print(curr.key + " ");
                    curr = curr.next;
                }

            StdOut.println();
            }
        }
    }
    
    /*
    public Iterable<Key> keys() {
        // See exercise 3.4.19
    }
    */

    public static void main(String[] args) {
        SeparateChainingHashST_EagerDelete<Character, Integer> st
            = new SeparateChainingHashST_EagerDelete<Character, Integer>();
        String str = "SEARCHXMPL";
        for (int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), i);
        }
        st.show();
        st.delete('R');
        StdOut.println();
        st.show();
    }
}


    

    
