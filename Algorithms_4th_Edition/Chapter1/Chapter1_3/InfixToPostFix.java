package Chapter1.Chapter1_3;

/*
Exercise 1.3.10 Page 162
Input: ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
Output: 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 */
public class InfixToPostFix {
    public static void main(String[] args) throws IllegalAccessException {
        String input = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
        String expectedOutput = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";

        String output = convert(input);
        System.out.println(output);

        assert output.equals(expectedOutput);
    }

    public static String convert(String str) throws IllegalAccessException {
        String[] chars = str.split(" ");
        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        for (String s : chars) {
            if (s.matches("[+-/*]")) operators.push(s);
            else if (s.equals(")")) {
                String operandOne = operands.pop();
                String operandTwo = operands.pop();
                String operator = operators.pop();
                operands.push(operandTwo + " " + operator + " " + operandOne + " )");
            } else if (!s.equals("(")) {
                operands.push(s);
            }
        }

        return operands.pop();
    }
}
