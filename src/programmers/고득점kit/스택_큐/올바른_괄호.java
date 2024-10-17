package programmers.고득점kit.스택_큐;

import java.util.*;

class 올바른_괄호 {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        boolean answer = true;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = false;
                    break;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(s.charAt(i));
            }
        }

        if (!stack.isEmpty())
            return false;
        return answer;
    }
}