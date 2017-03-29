/* 
   Exercises
   1.3.1: isFull()
*/

public class FixedCapacityStack<Item>
{
    private Item[] a; // stack entries
    private int N; // size

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }

    public boolean isFull() {
        return N == a.length;
    }
        
    public static void main(String[] args)
    {
        FixedCapacityStack<String> s;
        s = new FixedCapacityStack<String>(10);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
    }


        
}
