import java.util.Iterator;

public class LinkedListMerge {

    public static Node sort(Node head) {
        if (head == null || head.next == null) { return head; }
        
        Node middle = getMiddle(head); 
        Node sHalf = middle.next; middle.next = null;   //split the list
        
        return merge(sort(head), sort(sHalf));
    }

    public static boolean leq(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }
    
    // Merge two sorted lists
    public static Node merge(Node a, Node b) {
        Node dummyHead = new Node();
        Node curr = dummyHead;
        
        while (a !=null && b!= null) {
            //if (a.item <= b.item) {
            if (leq((Comparable)a.item, (Comparable)b.item)) {
                curr.next = a;
                a = a.next;
            }
            else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = (a == null) ? b : a;
        return dummyHead.next;
    }

    public static Node getMiddle(Node head) {
        if (head == null) { return head; }
        Node slow, fast;
        slow = fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        String[] a = "3862938476561".split("");
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        sort(list.head());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}
