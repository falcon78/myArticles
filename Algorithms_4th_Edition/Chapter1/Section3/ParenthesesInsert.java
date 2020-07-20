package Chapter1.Section3;

/*
Exercise 1.3.9 Pag 162
Input: 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
Output: ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 */
public class ParenthesesInsert {
    public static void main(String[] args) throws IllegalAccessException {
        String input = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        String expectedOutput = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";

        String output = insertLeftParentheses(input);
        System.out.println(output);

        assert output.equals(expectedOutput);
    }

    public static String insertLeftParentheses(String str) throws IllegalAccessException {
        String[] chars = str.split(" ");
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String s : chars) {
            if (s.matches("[+-/*]")) {
                operators.push(s);
            } else if (s.equals(")")) {
                String a = operands.pop();
                String b = operands.pop();
                String operator = operators.pop();
                operands.push("( " + b + " " + operator + " " + a + " )");
            } else {
                operands.push(s);
            }
        }

        return operands.pop();
    }

}
