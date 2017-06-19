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
        Node max = tail;
        Node current = tail;

        while (current.next != null) {
            if (less(max.item, current.next.item)) {
                max = current;
            }
        }

        Key k = max.item;
        max = max.next;
        N--;
        return k;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
    
    public void show() {
        Node current = tail;
        while (current != null) {
            current = current.next;
            StdOut.print(current.item + " ");
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        PriorityQueue_ULL<String> pq = new PriorityQueue_ULL<String>();
        pq.show();
        pq.insert("6");
        pq.show();
    }

        
}
        
