import java.util.Iterator;

public class LinkedListNaturalMerge {

    public static void sort(LinkedList<Comparable> list) {
  

        // sorted sub-lists' boundaries
        LinkedList<Comparable>.Node firstEnd;
        LinkedList<Comparable>.Node secondStart;
        LinkedList<Comparable>.Node secondEnd;

        // pointer used to find boundaries
        LinkedList<Comparable>.Node curr;
        
        while (true) {
            curr = list.head();
            
            // find the first sorted sub-list
            while (curr.next != null && !less(curr.next.item, curr.item)) {
                curr = curr.next;
            }

            // first sub-list spans whole list, sorted
            if (curr.next == null) { return; }

            firstEnd = curr;
            curr = curr.next;
            secondStart = curr;

            // find the second sorted sub-list
            while (curr.next != null && !less(curr.next.item, curr.item)) {
                curr = curr.next;
            }
            
            // either firstEnd or secondEnd should end the sorted sublists
            // sort out which one it should be in merge()
            secondEnd = curr.next;
            firstEnd.next = secondEnd;

            merge(list, secondStart, secondEnd);
        }
    }


    public static void merge(LinkedList<Comparable> list,
                             LinkedList<Comparable>.Node secondStart,
                             LinkedList<Comparable>.Node secondEnd) {

        LinkedList<Comparable>.Node first = list.head();
        LinkedList<Comparable>.Node second = secondStart;

        // points to start of list
        LinkedList<Comparable>.Node head = list.new Node(); 

        // p runs through the list moving .next pointers to the correct place
        LinkedList<Comparable>.Node p = head;
        
        while (first != secondStart && second != secondEnd) {
            if (second == null || first == null) {
                break;
            }
            
            if (less(second.item, first.item)) {
                p.next = second;
                second = second.next;
            }
            else {
                p.next = first;
                first = first.next;
            }
            p = p.next;
        }

        // sort out which node should be the end of the list
        if (second == secondEnd) {
            p.next = first;
        }
        else if (first == secondStart) {
            p.next = second;
        }

        // changing the head of the list doesn't seem like a good
        // idea but not sure what is the best way of doing it
        list.setHead(head.next);
    }

    public static void show(LinkedList l) {
        Iterator it = l.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next() + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(LinkedList<Comparable> l) {
        LinkedList<Comparable>.Node n = l.head();
        while (n.next != null) {
            if (less(n.next.item, n.item)) { return false; }
            n = n.next;
        }
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {        
        for (int i = 0; i < 100000; i++) {
            LinkedList<Comparable> list = new LinkedList();
            for (int j = 0; j < 100; j++) {
                list.add(StdRandom.uniform());
            }
            
            sort(list);
            if (!isSorted(list)) {
                StdOut.println("Error");
                break;
            }
             
        }
    }
}

        
