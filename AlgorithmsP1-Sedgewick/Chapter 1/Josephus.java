import java.util.Iterator;

public class Josephus {
    public static void main(String[] args) {
        Queue<Integer> q1 = new Queue<Integer>();
        Queue<Integer> q2 = new Queue<Integer>();
        Queue<Integer> current = q1;
        Queue<Integer> storage = q2;
        
        String killed = "";
        
        int N = 7;
        int M = 2;

        if (args.length > 2) {
            N = Integer.parseInt(args[1]);
            M = Integer.parseInt(args[0]);
        }
        
        for (int i = 0; i < N; i++) {
            q1.enqueue(i);
        }

        // Iterate through the queue. Every M-th interval, put item on
        // kill list. Put the rest of the items on second queue.

        int counter = 1;
        while (!q1.isEmpty() || !q2.isEmpty()) {
            while (!current.isEmpty()) {
                if (counter % M == 0) {
                    killed += current.dequeue() + " ";
                }
                else {
                    storage.enqueue(current.dequeue());
                }
                counter++;
            }

            Queue<Integer> temp = current;
            current = storage;
            storage = temp;
        }

        System.out.println(killed);
    }
}
