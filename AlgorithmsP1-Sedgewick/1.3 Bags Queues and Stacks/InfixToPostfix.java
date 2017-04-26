/* 1.3.10 Convert an infix expression to postfix
   This is the shunting-yard algorithm
   https://en.wikipedia.org/wiki/Shunting-yard_algorithm#The_algorithm_in_detail
*/

public class InfixToPostfix
{

    public static void main(String[] args) {
        OperatorTable opTable = new OperatorTable();
        
        Stack<String> stack = new Stack<String>();
        String output = "";
        String in = "";
        
        System.out.println("Enter an infix expression and I will " +
                           "convert it into an postfix expression.");
        System.out.print("> ");

        // assuming expression is well-formed, so doesn't test
        // for unknown chars, mismatched brackets etc.
        
        while(!StdIn.isEmpty()) {
            in = StdIn.readString();
            if (opTable.isOperator(in)) {
                while (!stack.isEmpty()) {
                    // added peek() in previous exercise
                    String op2 = stack.peek();

                    if (!opTable.isOperator(op2)) { break; }
                    
                    // pop from stack to output until stack is empty
                    // or conditions don't match
                    if (((opTable.associativity(in).equals("left")) &&
                         (opTable.compare(in, op2) < 1)) ||
                        ((opTable.associativity(in).equals("right")) &&
                         (opTable.compare(in, op2) == -1))) {

                        output += " " + stack.pop();
                    }
                    else {
                        break;
                    }
                }
                stack.push(in);
            }
            else if (in.equals("(")) {
                stack.push(in);
            }
            else if (in.equals(")")) {
                String op2 = stack.pop();
                while (!op2.equals("(")) {
                    output += " " + op2;
                    op2 = stack.pop();
                }
            }
            else { // input is a value
                output += " " + in;
            }
        }

        // push whatever's left on the stack to output
        while (!stack.isEmpty()) {
            output += " " + stack.pop();
        }

        System.out.println(output);
    }
}
