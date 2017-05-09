import java.util.Scanner;

public class Parentheses {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Character> stack = new Stack<Character>();
        String leftParen = "[{(";
        String rightParen = "]})";
        
        System.out.println("Enter a series of parentheses " +
                           "and I will tell you if they are properly " +
                           "balanced. \nNon-parentheses characters will " +
                           "be ignored.");
        System.out.print("> ");
        String s = input.nextLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // push left parentheses onto stack
            if (leftParen.indexOf(c) >= 0) {
                stack.push(c);
            }
            // if we have a right parens, pop() it's matching partner
            // from the stack or return as non-matching.
            else {
                int right = rightParen.indexOf(c);
                if (right >= 0) {
                    
                    char next = stack.pop();
                    int left = leftParen.indexOf(next);
                    
                    // doesn't match, quit
                    if (right != left) {
                        System.out.println("The parentheses don't match at "+
                                           "position " + (i+1));
                        return;
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("All matching!");
        }
        else {
            System.out.println("You have an extra left parentheses on the stack.");
        }
    }
}

