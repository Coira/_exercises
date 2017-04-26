/* Linked List for use with the linked list exercises. */

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item> {
    
    private class Node {
        Item item;
        Node next;
    }
    
    private Node first;
    private Node last;
    private int N = 0;
    
    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    
    // add an Item to the end of the list
    public void add(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) { first = last; }
        else { oldlast.next = last; }
        N++;
    }

    public Item remove() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) { last = null; }
        N--;
        return item;
    }

    // return the ith node, or first node if i < 0,
    // or last node if i > list size
    public Node get(int i) {
        if (i <= 0) { return first; }
        if (i >= size()) { return last; }
        
        Node current = first;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }

        return current;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() { }
    }

    /* Exercises */

    // 1.3.24
    public void removeAfter(Node node) {
        if (node == null || node.next == null) { return; }
        node.next = node.next.next;
    }

    // 1.3.25
    // Deviates slightly from the exercise as I can't access Node
    // from main
    public void insertAfter(Node n1, Item item) {
        if (item == null || item == null) { return; }

        Node n2 = new Node();
        n2.item = item;
        
        Node temp = n1.next;
        n1.next = n2;
        n2.next = temp;
    }

    // 1.3.26
    public void remove(String key) {
        // special case if first node in list contains the key
        if (first.item.equals(key)) {
            first.item = null;
            first = first.next;
        }

        // iterate through the list and remove any "key" nodes
        Node current = first;
        while (current != null && current.next != null) {
            if (current.next.item.equals(key)) {
                removeAfter(current);
            }
            current = current.next;
        } 
    }

    // 1.3.27
    public int max() {
        if (isEmpty()) { return 0; }
        
        Node current = first;
        int max = -1;

        while (current != null) {
            if ((Integer)current.item > max) {
                max = (Integer)current.item;
            }
            current = current.next;
        }

        return max;
    }

    // helper function for 1.3.28
    private int recur(Node n, int max) {
        if (n == null) { return max; }
        else {
            if ((Integer)n.item > max) {
                max = (Integer)n.item;
            }
            return recur(n.next, max);
        }
    }

    // 1.3.28
    public int maxRecursive() {
        if (isEmpty()) { return 0; }

        return recur(first, -1);
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        String[] chars = "xaxbxcxdxexfxgxhx".split("");
        for (int i = 0; i < chars.length; i++) {
            list.add(chars[i]);
        }

        list.remove("x");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) { System.out.println(it.next()); }

        LinkedList<Integer> ilist = new LinkedList<Integer>();
        ilist.add(100);
        for (int i = 0; i < 10; i++) {
            ilist.add(i);
        }

        System.out.println(ilist.maxRecursive());
    }
        
}
