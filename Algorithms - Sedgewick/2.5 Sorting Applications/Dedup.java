public class Dedup {
    public static String[] dedup(String[] a) {
        
        M3Quick.sort(a);
        
        String[] res = new String[a.length];
        int resIndex = 0;
        
        // Use 2 pointers to iterate through the array.
        // i points to the word we're adding to res, and j
        // is moved forward through any duplicates of that word
        int i = 0;  
        int j = 0; 
        
        while (i < a.length) {
            for (j = i+1; j < a.length && equals(a[i], a[j]); j++)
                ;
            res[resIndex++] = a[i];
            i = j;
        }

        // resize res
        String[] temp = new String[resIndex];
        for (i = 0; i < resIndex; i++) {
            temp[i] = res[i];
        }

        return temp;
    }

    private static boolean equals(String a, String b) {
        return a.compareTo(b) == 0;
    }
}
