/* 1.3.9: Inserts left parentheses into an infix expression */

public class LeftParen
{
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>(); // operator
        Stack<String> vals = new Stack<String>(); // operands

        // temporary storage for adding parens and putting
        // equation into the right order
        Stack<String> temp = new Stack<String>();
        
        String s = "";
        
        System.out.println("Enter an infix expression without left parentheses " +
                           "and I will fix it for you.");
        System.out.print("> ");

        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            
            if (s.equals(")")) { // add left paren
                String op2 = vals.pop();
                String op1 = vals.pop();
                String operator = ops.pop();
                String exp = "(" + op1 + operator + op2 + ")";
                vals.push(exp);
            }
            else if (s.equals("+") || s.equals("-") ||
                     s.equals("*") || s.equals("/")) {
                ops.push(s);
            }
            else vals.push(s);
                
        }
        System.out.println(vals.pop());
    }       
                
}
