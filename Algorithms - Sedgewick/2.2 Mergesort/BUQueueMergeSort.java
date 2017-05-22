import java.util.Iterator;

public class BUQueueMergeSort {
    public static Queue<Comparable> sort(Comparable[] a) {
        int N = a.length;
        Queue<Queue> qs = new Queue(); // queue of queues
        
        for (int i = 0; i < N; i++) {
            Queue<Comparable> q = new Queue();
            q.enqueue(a[i]);
            qs.enqueue(q);
        }

        while (qs.size() > 1) {
            qs.enqueue(QueueMerge.merge(qs.dequeue(), qs.dequeue()));
        }

        return qs.dequeue();
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    
    public static void main(String[] args) {
        String[] a = "BUQUEUEMERGESORTEXAMPLE".split("");
        Queue q = sort(a);
        Iterator it = q.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}
