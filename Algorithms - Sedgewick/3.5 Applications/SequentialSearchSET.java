public class SequentialSearchSET<Key> {
    private Node first;
    private int N = 0;
    
    public class Node {
        Key key;
        Node next;
        public Node(Key key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(Key key) {
        Node curr = first;
        while (curr != null) {
            if (curr.key.compareTo(key) == 0) return true;
            curr = curr.next;
        }
        return false;
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

        if (!contains(key)) return;
        
        if (first.key.equals(key)) {
            first = first.next;
            N--;
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
        N--;
    }

    public void add(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return;
            }
        }
        N++;
        first = new Node(key, first); // search miss
    }

    public static void main(String[] args) {
        String[] str = "AWKFHCNH".split("");
        SequentialSearchSET<String> set = new SequentialSearchSET<String>();
        for (int i = 0; i < str.length; i++)
            set.add(str[i]);
        StdOut.println(set.size());

        for (String s : set.keys()) {
            StdOut.print(s + " ");
        }
        StdOut.println();

        for (int i = 0; i < str.length; i++)
            set.delete(str[i]);
        StdOut.println(set.size());
    }
}


            
