/* 2.2.14: Merges 2 sorted queues */

import java.util.Iterator;

public class QueueMerge {
    public static Queue<Comparable> merge(Queue<Comparable> a,
                                          Queue<Comparable> b) {
        Queue<Comparable> dst = new Queue();

        while (!a.isEmpty() && !b.isEmpty()) {
            if (less(a.peek(), b.peek())) {
                dst.enqueue(a.dequeue());
            }
            else {
                dst.enqueue(b.dequeue());
            }
        }

        // if any of the queues have any item left, copy them over
        while (!a.isEmpty()) {
            dst.enqueue(a.dequeue());
        }
        while (!b.isEmpty()) {
            dst.enqueue(b.dequeue());
        }

        return dst;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Queue<Comparable> a = new Queue();
        Queue<Comparable> b = new Queue();
        //Queue dst = new Queue();
        
        a.enqueue(1);
        b.enqueue(2);
        a.enqueue(3);
        b.enqueue(4);
        a.enqueue(5);
        b.enqueue(6);

        Queue<Comparable> dst = merge(a, b);

        Iterator it = dst.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
                              
}
