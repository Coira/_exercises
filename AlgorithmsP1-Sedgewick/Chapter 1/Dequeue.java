import java.util.Iterator;

public class Dequeue<Item> implements Iterable<Item> {
    private class DNode {
        private DNode left;
        private DNode right;
        private Item item;
    }

    private DNode first;   // points to the leftmost node
    private DNode last;   // points to the rightmost node
    private int N = 0;

    public boolean isEmpty() { return first == null || last == null; }
    public int size() { return N; }

    public void pushLeft(Item item) {
        DNode oldfirst = first;
        first = new DNode();
        first.item = item;
        first.right = oldfirst;
        first.left = null;
        
        if (isEmpty()) { last = first; }
        else { oldfirst.left = first; }

        N++;
    }

    public void pushRight(Item item) {
        DNode oldlast = last;
        last = new DNode();
        last.item = item;
        last.left = oldlast;
        last.right = null;

        if (isEmpty()) { first = last; }
        else { oldlast.right = last; }

        N++;
    }

    public Item popLeft() {
        Item item = first.item;
        first = first.right;
        first.left = null;
        if (isEmpty()) { last = null; }
        N--;
        return item;
    }

    public Item popRight() {
        Item item = last.item;
        last = last.left;
        last.right = null;
        if (isEmpty()) { first = null; }
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // iterates from left to right
    private class ListIterator implements Iterator<Item> {
        private DNode current = first;
        public boolean hasNext() { return current != null; }
        public Item next() {
            Item item = current.item;
            current = current.right;
            return item;
        }
        public void remove() { }
    }

     public static void main(String[] args) {
        Dequeue<String> q = new Dequeue<String>();
        boolean pos = false;
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                if (pos) { q.pushLeft(item); }
                else { q.pushRight(item); }
                pos = !pos;
            }
            else  {
                if (q.isEmpty()) { StdOut.println("Nothing to pop."); }
                else { StdOut.println(q.popRight() + " " ); }
            }
        }

        Iterator<String> it = q.iterator();
        System.out.println("This is what is left on the steque: ");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
    }
}

    
