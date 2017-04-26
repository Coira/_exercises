import java.util.Iterator;

public class ResizingArrayQueueOfStrings<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private int last = 0;
    private int first = 0;

    private void resize(int size) {
        Item[] temp = (Item[]) new Object[size];

        for (int i = 0, j = first; i < N; i++, j++) {
            if (j == a.length) { j = 0; }
            temp[i] = a[j];
        }

        a = temp;
        first = 0;
        last = N;
    }

    public boolean isEmpty() { return first == last; }
    public int size() { return last - first; }
    
    public void enqueue(Item item) {
        a[last++] = item;
        N++;
        if (last == a.length) { last = 0; } // circular array
        if (N == a.length) { resize(a.length*2); }
    }

    public Item dequeue() {
        Item item = a[first];
        a[first++] = null;
        if (first == a.length) { first = 0; } // circular array
        N--;
        if (N > 0 && N == a.length/4) { resize(a.length/2); }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() { return i < N; }
        public Item next() {
            int index = (i + first) > a.length ? i : i + first;
            Item item = a[index];
            i++;
            return item;
        }
        public void remove() { }
    }

}
