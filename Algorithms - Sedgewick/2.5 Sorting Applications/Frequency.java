/* 2.5.8: Frequency of strings */

import java.util.ArrayList;

public class Frequency {
    public static void frequency(String[] str) {

        M3Quick.sort(str);

        // I could use a hash table instead of two arrays,
        // but in keeping with the material we've covered so far...
        String[] key = new String[str.length];
        int[] freq = new int[str.length];
        int index = 0;

        // Similiar to dedup(), two pointers; i points to key,
        // j counts the number of duplicates
        int i, j;
        i = j = 0;

        while (i < str.length) {
            int f = 1;  // frequency
            j = i + 1;
            for (j = i+1; j < str.length && equals(str[i], str[j]); j++)
                f++;
            key[index] = str[i];
            freq[index] = f;
            index++;
            i = j;
        }

        show(key, freq, index);
    }

    private static boolean equals(String a, String b) {
        return a.compareTo(b) == 0;
    }
    
    private static void show(String[] key, int[] freq, int N) {
        StdOut.println("Frequency of words: "); 
        for (int i = N-1; i >= 0; i--) {
            StdOut.println(key[i] + ": " + freq[i]);
        }
        StdOut.println();
    }
    
    
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
        String[] str;
        while (!StdIn.isEmpty()) {
            a.add(StdIn.readString());
        }
        str = new String[a.size()];
        a.toArray(str);
        frequency(str);
    }
}


        
