public class BSItem<Key extends Comparable<Key>, Value> {
    public Key key;
    public Value val;
    
    public BSItem(Key key, Value val) {
        this.key = key;
        this.val = val;
    }
}
