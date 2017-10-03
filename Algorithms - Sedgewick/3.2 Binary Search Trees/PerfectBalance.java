import java.util.Arrays;

public class PerfectBalance {
    public static BST<String, Integer> perfectBalance(String[] str) {
        BST<String, Integer> bst = new BST<String, Integer>();
        perfectBalance(bst, 0, str.length-1, str);
        return bst;
    }

    private static void perfectBalance(BST<String, Integer> bst,
                                       int lo, int hi, String[] str) {
        if (hi <= lo) return;
        
        int mid = (hi+lo)/2;
        bst.put(str[mid], mid);
        perfectBalance(bst,lo,mid,str);
        perfectBalance(bst,mid+1,hi,str);
    }
                                       
    public static void main(String[] args) {
        String[] str = "BINARYSEARCHPFTL".split("");
        Arrays.sort(str);

        BST<String, Integer> bst = perfectBalance(str);
                    
    }
}
