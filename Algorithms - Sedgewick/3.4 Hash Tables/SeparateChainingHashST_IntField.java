public class SeparateChainingHashST_IntField<Key, Value> {
    
    public class Node{
        Key key;
        Value val;
        Node next;
        int intField; // Ex. 3.4.3 Number of items in table at time of insert
        public Node() { }
        public Node(Key key, Value val, Node next, int intField) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.intField = intField;
        }
    }
    
    private int M;   // hash table size
    private Node first; // start of hash table
    private int N = 0;
    
    //private SequentialSearchST<Key, Value>[] st;  // array of ST objects
    private Node[] hashTable;
    
    public SeparateChainingHashST_IntField() {
        //this(997); // 997 lists
        this(5);
    }

    public SeparateChainingHashST_IntField(int M) {
        // Create M linked lists.
        this.M = M;
        hashTable = new SeparateChainingHashST_IntField.Node[M];
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
                n.intField = N++;
                return;
            }
        }
        first = new Node(key, val, first, N++);
        hashTable[h] = first;
    }

    // Delete all Nodes with int field greater than k
    public void delete(int k) {
        for (int i = 0; i < M; i++) {

            // Delete items from the start of the list
            Node first = hashTable[i];
            while (first != null && first.intField > k) {
                first = first.next;
            }
            hashTable[i] = first;

            // Delete items from the rest of the list
            Node curr = first; // don't need to check first, done so above
            Node temp;

            while (curr != null && curr.next != null) {
                temp = curr.next;
                if (temp.intField > k) {
                    curr.next = temp.next;
                }
                else {
                    curr = curr.next;
                }
            }            
        }
    }
                
                    

                
    public void show() {
        for (int i = 0; i < M; i++) {
            Node curr = hashTable[i];
   
            if (curr != null) {
                StdOut.print(i + ": ");
                while (curr != null) {
                    StdOut.print("(" + curr.key + " " + curr.intField + ")");
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
        SeparateChainingHashST_IntField<Character, Integer> st
            = new SeparateChainingHashST_IntField<Character, Integer>();
        String str = "ANRSTINHDARSNEISIIIIIINNNNNNN";
        for (int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), i);
        }
        st.show();
        StdOut.println();
        st.delete(20);
        st.show();
    }
}


    

    
