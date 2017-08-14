/*
  3.1.3
  A symbol table which uses an ordered linked list as its
  underlying data structure.
*/

import java.util.Iterator;
                          
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first = null;
    private int N = 0;
    
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node() { }

        public Node (Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    boolean isEmpty() { return first == null; }
    int size() { return N; }
    
    public void put(Key key, Value val) {
        
        Node node = new Node(key, val, null);

        // we can delete a key by equating its value with null
        if (val == null) {
            delete(key);
            return;
        }
        
        if (isEmpty()) {
            first = node;
            N++;
            return;
        }

        // if key already exists, update value
        Node prev = first;
        Node x;
        for (x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
            prev = x;
        }

        
        // otherwise insert new node into the correct place
        // as list is already sorted, no need to re-sort, just insert
        prev = first;
        for (x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) > 0) {
                if (x == first) {
                    first = node;
                    node.next = x;
                }
                else {
                    prev.next = node;
                    node.next = x;
                }
                break;
            }
            prev = x;
        }

        // new node belongs at the end of the list
        if (x == null) {
            prev.next = node;
        }

        N++;
    }

    public Value get(Key key) {
        Node curr = first;
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.val;
            }
            curr = curr.next;
        }
        return null;
    }

    public void delete(Key key) {
        
        if (first.key.equals(key)) {
            first = first.next;
            return;
        }

        Node curr = first;
        while (curr != null && curr.next != null) {
            if (curr.next.key.equals(key)) {
                Node temp = curr.next.next;
                curr.next.next = null;
                curr.next  = temp;
                break;
            }
            curr = curr.next;
        }
    }

    public boolean contains(Key key) {
        Node curr = first;
        while (curr != null) {
            if (curr.key.equals(key)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public Key min() {
        if (isEmpty()) return null;
        return first.key;
    }

    public Key max() {
        if (isEmpty()) return null;
        Node curr = first;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr.key;
    }

    public Key floor(Key key) {
        if (first.key.compareTo(key) > 0) { return null; }
        
        Node curr = first;
        while (curr != null && curr.next != null) {
            if (curr.next.key.compareTo(key) > 0) {
                return curr.key;
            }
            curr = curr.next;
        }
        return curr.key;
    }

    public Key ceiling(Key key) {
        Node curr = first;
        while (curr != null) {
            if (curr.key.compareTo(key) >= 0) {
                return curr.key;
            }
            curr = curr.next;
        }
        return null;
    }

    public int rank(Key key) {
        // doesn't check that key exists
        Node curr = first;
        int count = 0;
        while (curr != null && curr.key.compareTo(key) < 0) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public Key select(int k) {
        // doesn't check rank k exists
        Node curr = first;
        int i = 0;
        while (curr != null && i < k) {
            i++;
            curr = curr.next;
        }
        return curr.key;
    }

    public void deleteMin() {
        if (!isEmpty()) {
            first = first.next;
        }
    }

    public void deleteMax() {
        delete(max());
    }
                    
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        Node curr = first;
        while (curr != null) {
            q.enqueue(curr.key);
            curr = curr.next;
        }
        return q;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        
        Node curr = first;
        while(curr != null && curr.key.compareTo(lo) < 0) {
            curr = curr.next;
        }
   
        while (curr != null && curr.key.compareTo(hi) <= 0) {
            
            q.enqueue(curr.key);
            curr = curr.next;
        }
        return q;
    }

    public int size(Key lo, Key hi) {
        int count = 0;
        Node curr = first;
        while (curr != null && curr.key.compareTo(lo) < 0) {
            curr = curr.next;
        }
        
        while (curr != null && curr.key.compareTo(hi) <= 0) {
            count++;
            curr = curr.next;
        }
        return count;
    }
         
            
    public static void main(String[] args) {
    }
}
