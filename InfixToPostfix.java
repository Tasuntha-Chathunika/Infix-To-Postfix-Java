import java.util.Stack;

public class InfixToPostfix {

     
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

     
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "Error: Mismatched parentheses";
                }
            } else {  
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    if (stack.peek() == '(')
                        break;
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

         
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Error: Mismatched parentheses";
            result.append(stack.pop());
        }

        return result.toString();
    }

     
    public static void main(String[] args) {
        String infix = "A+(B*C)";
        String postfix = infixToPostfix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
    }
}
