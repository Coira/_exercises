/*
API:
public class Steque<Item> implements Iterable<Item>
        Steque()    create an empty steque
boolean isEmpty()   is the steque empty?
    int size()      number of items in the steque
   void push()      add an item to the end of the steque
   Item pop()       remove an item from the end of the steque
   Item enqueue()   add an item to the start of the steque
*/

import java.util.Iterator;

public class Steque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private Node last;
    private int N = 0;

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public void push(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = oldlast;
        if (isEmpty()) { first = last; }
        N++;
    }

    public void enqueue(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = null;

        if (oldfirst == null) { last = first; }
        else { oldfirst.next = first; }

        N++;
    }

    public Item pop() {
        Item item = last.item;
        last = last.next;
        if (last == null) { first = null; }
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = last;
        public boolean hasNext() { return current != null; }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() { }
    }

    public static void main(String[] args) {
        Steque<String> q = new Steque<String>();
        boolean pos = true; // if this is true, push, otherwise enqueue
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                if (pos) { q.enqueue(item); }
                else { q.push(item); }
                pos = !pos;
            }
            else  {
                if (q.isEmpty()) { StdOut.println("Nothing to pop."); }
                else { StdOut.println(q.pop() + " " ); }
            }
        }

        Iterator<String> it = q.iterator();
        System.out.println("This is what is left on the steque: ");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
    }
        
}
