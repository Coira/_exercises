public class BinarySearchST_WithItem<Key extends Comparable<Key>, Value> {
   
    private BSItem[] items;
    private int N = 0;

    public class BSItem implements Comparable<BSItem> {
        public Key key;
        public Value val;
        
        public BSItem(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(BSItem item) {
            return this.key.compareTo(item.key);
        }

        public int compareTo(Key key) {
            return this.key.compareTo(key);
        }
    }

    public BinarySearchST_WithItem() {
    }

    public BinarySearchST_WithItem(int capacity) {
        // array resizing omitted
        items = new BinarySearchST_WithItem.BSItem[capacity];
    }

    
    public BinarySearchST_WithItem(BSItem[] items) {
        Merge.sort(items);
        
        this.items = new BinarySearchST_WithItem.BSItem[items.length];
        this.items[N++] = items[0];
        
        for (int i = 1; i < items.length; i++) {
            // we can omit call to put() and add items to array directly
            //if (this.items[N-1].key.compareTo(items[i].key) == 0) {
            if (this.items[N-1].compareTo(items[i]) == 0) {
                // key already exists, overwrite value
                this.items[N-1].val = items[i].val;
            }
            else {
                this.items[N++] = items[i];
            }
        }
    }
    
    public int size() { return N; }

    public boolean isEmpty() { return N == 0; }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < N && items[i].compareTo(key) == 0) {
            items[i].val = val;
            return;
        }

        for (int j = N; j > i; j--) {
            items[j] = items[j-1];
        }
        items[i] = new BSItem(key,val);
        N++;

    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            int cmp = items[mid].compareTo(key);
            if (cmp > 0) hi = mid - 1;
            else if (cmp < 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && items[i].compareTo(key) == 0)
            return items[i].val;
        else
            return null;
    }

    public void delete(Key key) {
        int i = rank(key);
        if (i < N && items[i].compareTo(key) == 0) {
            for (int j = i+1; j < N; j++) {
                items[j-1] = items[j];
            }
        }
        N--;
    }

    public Key min() {
        return items[0].key;
    }

    public Key max() {
        return items[N-1].key;
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return items[i].key;
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && items[i].compareTo(key) == 0) {
            return items[i].key;
        }
        else if (i > 0) {
            return items[i-1].key;
        }
        else {
            return null;
        }
    }
            
    public void show() {
        for (int i = 0; i < N; i++) {
            StdOut.println("(" + items[i].key + "," + items[i].val + ")");
        }
    }
    
    public static void main(String[] args) {

        String s[] = "ACYANFBPWENVYABN".split("");
        


        BinarySearchST_WithItem.BSItem[] items = new BinarySearchST_WithItem.BSItem[s.length];

        for (int i = 0; i < s.length; i++) {
            items[i] =
                new BinarySearchST_WithItem().new BSItem(s[i], i);
        }
        BinarySearchST_WithItem<String, Integer> st= new BinarySearchST_WithItem(items);
        st.delete("A");
        st.delete("Y");
        st.delete("N");
        st.put("C",3);
        st.show();
    }

}
