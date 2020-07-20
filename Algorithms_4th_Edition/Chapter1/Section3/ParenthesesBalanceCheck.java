package Chapter1.Section3;

/*
Exercise 1.3.4 Pag 161
 */
public class ParenthesesBalanceCheck {
    public static void main(String[] args) {
        String str = "[()]{}{[()()]()}()";
        assert isBalanced(str);

        assert !isBalanced("[(])");

    }

    public static boolean isBalanced(String str) {
        if (str.length() % 2 != 0) return false;

        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                char pop;
                try {
                    pop = stack.pop();
                } catch (IllegalAccessException e) {
                    return false;
                }

                if (c == ')' && pop != '(') return false;
                else if (c == '}' && pop != '{') return false;
                else if (c == ']' && pop != '[') return false;
            }
        }

        return true;
    }
}
