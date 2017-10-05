public class BST_Reconstruction {
    public static BST preorderRecon(Queue<BST.Node> q) {
        // reconstructing a queue made from preorder traversal
        // of a tree is simple, we only need to iterate through
        // the queue

        BST bst = new BST();
        while (!q.isEmpty()) {
            BST.Node n = q.dequeue();
            bst.put(n.key, n.val);
        }

        return bst;
    }

    public static BST postorderRecon(Queue<BST.Node> q) {
        // first we need to reverse the queue, then
        // we can put all its items back on the tree

        BST bst = new BST();
        BST.Node[] arr = new BST.Node[q.size()];

        for (int i = 0; !q.isEmpty(); i++) {
            arr[i] = q.dequeue();
        }

        // reverse queue
        for (int i = 0; i < arr.length/2; i++) {
            BST.Node temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }

        // create new BST
        for (int i = 0; i < arr.length; i++) {
            bst.put(arr[i].key, arr[i].val);
        }
        
        return bst;
    }
    
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<String, Integer>();
        
        String[] str = "EASYQUESTION".split("");
        for (int i = 0; i < str.length; i++) {
            bst.put(str[i], i);
        }

        Queue qPre = bst.preorderTraversal();
        BST bstPre = preorderRecon(qPre);

        Queue qPost = bst.postorderTraversal();
        BST bstPost = postorderRecon(qPost);

    }
}
