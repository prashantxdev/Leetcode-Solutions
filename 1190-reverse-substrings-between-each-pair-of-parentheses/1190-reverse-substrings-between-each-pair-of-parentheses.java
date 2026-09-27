import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < sb.length(); i++) {

            if (sb.charAt(i) == '(') {
                stack.push(i);
            } 
            else if (sb.charAt(i) == ')') {
                int start = stack.pop();

                reverse(sb, start + 1, i - 1);
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '(' && sb.charAt(i) != ')') {
                ans.append(sb.charAt(i));
            }
        }

        return ans.toString();
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);

            left++;
            right--;
        }
    }
}