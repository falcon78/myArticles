package Chapter1.Chapter1_3;

public class EvaluatePostFix {
    public static void main(String[] args) throws IllegalAccessException {
        String expression;
        int result;
        try {
            expression = args[0];
            result = evaluate(expression);
        } catch (IndexOutOfBoundsException e) {
            expression = "1 2 + 3 4 - 5 6 - * *";
            result = evaluate(expression);
            assert result == 3;
        }

        System.out.println(result);
    }

    public static int evaluate(String expression) throws IllegalAccessException {
        Stack<Integer> operands = new Stack<>();
        String[] expressionArray = expression.split("\\s");

        for (String s : expressionArray) {
            if (s.matches("[+-/*]")) {
                int operandTwo = operands.pop();
                int operandOne = operands.pop();

                if (s.equals("+")) operands.push(operandOne + operandTwo);
                if (s.equals("-")) operands.push(operandOne - operandTwo);
                if (s.equals("*")) operands.push(operandOne * operandTwo);
                if (s.equals("/")) operands.push(operandOne / operandTwo);
            } else {
                operands.push(Integer.parseInt(s));
            }
        }

        return operands.pop();
    }
}
