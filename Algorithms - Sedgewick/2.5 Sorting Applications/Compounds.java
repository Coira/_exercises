/* 2.5.2: Find compound words */

public class Compounds {
    public static String[] findCompounds(String[] words) {

        int N = words.length;
        int found = 0; // number of compounds found
        String[] compounds = new String[words.length];
        String[] possibleCompounds = new String[words.length];

        sort(words);
        
        for (int i = 0; i < words.length; i++) {
            int basesFound = 0;
            String base = words[i];
            int baseLength = base.length();

            // get a list of words that start with 'base'
            for (int j = i+1; j < words.length; j++) {
                if (words[j].startsWith(base)) {
                    possibleCompounds[basesFound++] = words[j];
                }
            }

            // check possibleCompounds for strings that also end with
            // one of the words in wordList
            for (int j = 0; j < basesFound; j++) {
                int pcLen = possibleCompounds[j].length();
                for (int k = 0; k < words.length; k++) {
                    if (pcLen != baseLength + words[k].length())
                        continue;
                    else if (possibleCompounds[j].endsWith(words[k]))
                        compounds[found++] = possibleCompounds[j];
                }
                
                        
            }
        }

        // resize compounds array
        String[] temp = new String[found];
        for (int i = 0; i < found; i++) {
            temp[i] = compounds[i];
        }
        return temp;
    }


    private static void sort(String[] words) {
        // insertion sort
        for (int i = 1; i < words.length; i++) {
            for (int j = i; j > 0 && compare(words[j], words[j-1]) < 0; j--) {
                exch(words, j, j-1);
            }
        }
    }

    private static void exch(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int compare(String a, String b) {
        return a.compareTo(b);
    }

    public static void show(String[] words) {
        for (int i = 0; i < words.length && words[i] != null; i++) {
            StdOut.print(words[i] + " ");
        }
        StdOut.println();
    }
    
    public static void main(String[] args) {
        String str = "";
        while (!StdIn.isEmpty()) {
            str += StdIn.readString() + " ";
        }
        String[] compounds = findCompounds(str.split(" "));
        StdOut.println("\nFound the following compounds: ");
        show(compounds);

    }
}
