public class PerfectHash {
    public static int hash(int a, int k, int M) {
        
        return (a * k) % M;
    }
    
    public static void main(String[] args) {
        String str = "SEARCHXMPL";
        
        int M = str.length();
        
        while(true) {
            for (int a = 1; a < 1000; a++) {

                // if arr[hash] already has an item in it, collision has
                // occured and we can move to the next loop
                int[] arr = new int[M];
                for (int i = 0; i < arr.length; i++)
                    arr[i] = 0;
                
                int k;
                for (k = 0; k < str.length(); k++) {
                    int h = hash(a, str.charAt(k)-'A', M);
                    if (arr[h] != 0) { break; } // collision
                    arr[h]++;
                }
                // reached the end of str without a collision
                if (k == str.length()) {
                    StdOut.println(a + " " + M);
                    return;
                }
            }
            M++; // increase array size
        }
    }

    // a = 1, M = 20
}
