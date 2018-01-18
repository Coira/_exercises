/*
  Symbol table using linked lists and sequential search.
*/

public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N = 0;
    
    public class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        return N;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        Node curr = first;
        while (curr != null) {
            q.enqueue(curr.key);
            curr = curr.next;
        }
        return q;
    }

    public void delete(Key key) {
        
        if (first.key.equals(key)) {
            first = first.next;
            return;
        }

        Node curr = first;
        while (curr != null && curr.next != null) {
            if (curr.next.key.equals(key)) {
                Node temp = curr.next.next;
                curr.next.next = null;
                curr.next  = temp;
                break;
            }
            curr = curr.next;
        }
    }
        
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val; // search hit
        }
        return null;      // search miss
    }

    public void put(Key key, Value val) {
        
        if (val == null) {
            delete(key);
            return;
        }
        
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;   // search hit
                return;
            }
		}
        N++;
        first = new Node(key, val, first); // search miss
    }
}


            
