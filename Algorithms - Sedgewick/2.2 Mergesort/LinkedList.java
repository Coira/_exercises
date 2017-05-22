import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item>{
    // mostly acts like a queue
    public class Node {
        Node next;
        Item item;
    }

    private Node head;
    private Node tail;
    private int N = 0;

    public boolean isEmpty() { return head == null; }
    public int size() { return N; }
    public Node head() { return head; }
    public Node tail() { return tail; }

    public void setHead(Node n) {
        head = n;
    }
        
    // add to end of list
    public void add(Item item) {
        Node oldtail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        N++;
        if (isEmpty()) { head = tail; }
        else { oldtail.next = tail; }
    }

    // remove from start of list
    public Item remove() {
        Item item = head.item;
        head = head.next;
        if (isEmpty()) { tail = null; }
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = head;
        public boolean hasNext() { return current != null; }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
    

    
        
    
