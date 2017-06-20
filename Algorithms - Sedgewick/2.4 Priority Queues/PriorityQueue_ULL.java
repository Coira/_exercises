/*
  Max-value priority Queue.
  Unordered linked list implementation.
  Based on code for a linked list stack.
*/

public class PriorityQueue_ULL<Key extends Comparable<Key>>  {
    private class Node {
        Node next;
        Key item;
    }

    private Node tail;
    private int N = 0;

    public PriorityQueue_ULL() {
    }
    
    public PriorityQueue_ULL(Key[] a) {
        for (int i = 0; i < a.length; i++)
            insert(a[i]);
    }
    
    public boolean isEmpty() { return tail == null; }
    public int size() { return N; }

    public void insert(Key v) {
        Node oldtail = tail;
        tail = new Node();
        tail.item = v;
        tail.next = oldtail;
        N++;
    }

    public Key delMax() {
        Node dummyTail = new Node();
        dummyTail.next = tail;
        Node current = dummyTail;

        // points to the node *behind* the node that holds the
        // max value, so we can remove the max node by manipulating
        // the .next pointer
        Node toMax = tail;
        
        while (current.next != null) {
            if (less(toMax.next.item, current.next.item)) {
                toMax = current;
            }
            current = current.next;
        }
        
        Key k = toMax.next.item; 
        toMax = toMax.next;
        N--;
        return k;
 
    }

    public Key max() {
        Node current = tail;
        Key k = tail.item;
        while (current != null) {
            if (less(k, current.item)) {
                k = current.item;
            }
            current = current.next;
        }
        return k;
    }
                    

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
    
    public void show() {
        Node current = tail;
        while (current != null) {
            StdOut.print(current.item + " ");
            current = current.next;
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        String[] s = "3851769".split("");
        PriorityQueue_ULL<String> pq = new PriorityQueue_ULL<String>();
        for (int i = 0; i < s.length; i++) {
            pq.insert(s[i]);
        }
        pq.show();
        System.out.println(pq.delMax());
        pq.show();
        System.out.println(pq.max());
        pq.show();
    }

        
}
        
