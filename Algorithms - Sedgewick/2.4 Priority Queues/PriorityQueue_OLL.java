/*
  Max-value priority Queue.
  Ordered linked list implementation.
  Based on code for a linked list stack.
*/

public class PriorityQueue_OLL<Key extends Comparable<Key>>  {
    private class Node {
        Node next;
        Key item;
    }

    private Node tail;
    private int N = 0;

    public PriorityQueue_OLL() {
    }
    
    public PriorityQueue_OLL(Key[] a) {
        for (int i = 0; i < a.length; i++)
            insert(a[i]);
    }

    public boolean isEmpty() { return tail == null; }
    public int size() { return N; }
    
    public void insert(Key v) {
        Node node = new Node();
        node.item = v;
        node.next = null;
        
        if (isEmpty()) { tail = node; }
        else {
            Node dummyTail = new Node();
            dummyTail.next = tail;
            Node current = dummyTail;

            // move current to the position that needs to point to the new node
            while (current.next != null && less(v, current.next.item)) {
                current = current.next;
            }

            // insert key between the two nodes
            Node temp = current.next;
            current.next = node;
            node.next = temp;

            // update tail if we have to
            if (dummyTail.next != tail)
                tail = dummyTail.next;
        }
        N++;
    }

    public Key delMax() {
        Key key = tail.item;
        tail = tail.next;
        N--;
        return key;
    }

    public Key max() {
        return tail.item;
    }
    
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
    
    public void show() {
        Node curr = tail;
        while (curr != null) {
            StdOut.print(curr.item + " ");
            curr = curr.next;
        }
        StdOut.println();
    }
        

    public static void main(String[] args) {
        PriorityQueue_OLL<String> pq;// = new PriorityQueue_OLL<String>();
        String[] a = "48274959".split("");
        pq = new PriorityQueue_OLL<String>(a);
        pq.show();
    }     
}
        
