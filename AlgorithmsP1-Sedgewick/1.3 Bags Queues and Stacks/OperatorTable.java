/* 1.3.10 Operator table for InfixToPostfix */

import java.util.Hashtable;

public class OperatorTable {
    private Hashtable<String, Integer> precTable;
    private Hashtable<String, String> assocTable;
    
    private class Tuple<Integer, String> {
        public int precedence;
        public String associativity;
        
        public Tuple(int precedence, String associativity) {
            this.precedence = precedence;
            this.associativity = associativity;
        }
    }

    public OperatorTable() {
        
        // table of operators and their precedence
        precTable = new Hashtable<String, Integer>();
        precTable.put("^", 4);
        precTable.put("*", 3);
        precTable.put("/", 3);
        precTable.put("+", 2);
        precTable.put("-", 2);

        // table of operators and their associativity
        assocTable = new Hashtable<String, String>();
        assocTable.put("^", "right");
        assocTable.put("*", "left");
        assocTable.put("/", "left");
        assocTable.put("+", "left");
        assocTable.put("-", "left");
    }

    public int precedence(String op) {
        return precTable.get(op);
    }

    public String associativity(String op) {
        return assocTable.get(op);
    }

    // checks whether op is in one of the operator tables
    public boolean isOperator(String op) {
        return (precTable.containsKey(op) ||
                assocTable.containsKey(op));
    }

    /* returns -1 if precedence of op1 < precedence of op2
       returns 0 if precedence of op1 == precedence of op2
       returns 1 if precedence of op1 > precedence of op2 */
    public int compare(String op1, String op2) {
        int pr1 = precedence(op1);
        int pr2 = precedence(op2);
        
        if (pr1 < pr2) { return -1; }
        if (pr1 == pr2) { return 0; }
        else { return 1; }
    }
}
