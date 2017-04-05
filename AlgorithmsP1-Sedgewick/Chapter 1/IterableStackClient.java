/* 1.3.13: Write an iterable Stack client */
import java.util.Iterator;

public class IterableStackClient {
    public static Stack copy(Stack stack) {
        Iterator iter = stack.iterator();
        Stack temp = new Stack();
        Stack copyStack = new Stack();
        
        // this reverses the stack
        while (iter.hasNext()) {
            temp.push(iter.next());
        }

        // we have to repeat the process to get the stack
        // back into the original order
        // though perhaps that doesn't matter with stacks
        while (!temp.isEmpty()) {
            copyStack.push(temp.pop());
        }

        return copyStack;
    }

    public static void main(String[] args) {
        String[] testStr =
            "The big fat cat sat on the tiny mat".split(" ");
        Stack<String> stack = new Stack<String> ();

        for (int i = 0; i < testStr.length; i++) {
            stack.push(testStr[i]);
        }

        Stack<String> copystack = copy(stack);
        while (!copystack.isEmpty()) {
            System.out.println(copystack.pop());
        }
    }
}
