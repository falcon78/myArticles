package Chapter1.Section3;

/*
Exercise 3.1.12
 */
public class StackWithCopy<T> extends Stack<T> {
    public static void main(String[] args) throws IllegalAccessException {
        StackWithCopy<String> stack = new StackWithCopy<>();
        for (int i = 0; i < 10; i++) {
            stack.push("" + i);
        }

        for (String s : StackWithCopy.copy(stack)) {
            assert s.equals(stack.pop());
        }
    }

    public static Stack<String> copy(Stack<String> stack) {
        Stack<String> copy = new Stack<>();
        Stack<String> temp = new Stack<>();

        for (String s : stack) {
            temp.push(s);
        }

        for (String s : temp) {
            copy.push(s);
        }

        return copy;
    }

}
