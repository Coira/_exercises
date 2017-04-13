import java.util.Iterator;

public class CircularQueue<Item> implements Iterable<Item> {
    private class Node {
        Node next;
        Item item;
    }

    private Node last;
    private int N = 0;

    public boolean isEmpty() { return last == null; }
    public int size() { return N; }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;

        if (oldlast == null) { last.next = last; }
        else {
            last.next = oldlast.next;
            oldlast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        Item item = last.next.item;
        if (last.next == last) { last = null; } // empty queue
        else { last.next = last.next.next; }
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = last; // start here incase last is null
        public boolean hasNext() { return current != null; }
        public Item next() {
            Item item = current.next.item;
            if (current.next == current.next.next) { current = null; }
            else { current.next = current.next.next; }
            return item;
        }
        public void remove() { }
    }
        

    public static void main(String[] args) {
        // similar to the Queue client from the book, read
        // in user input, and dequeue when it reads '-'
        // afterwards, print out all that's left on the queue
        
        CircularQueue<String> q = new CircularQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty())
                StdOut.println(q.dequeue() + " " );
        }

        System.out.println("\nThe following is left on the queue:");
        Iterator<String> it = q.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

    
                                            
