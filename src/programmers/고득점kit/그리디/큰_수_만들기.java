package programmers.고득점kit.그리디;

import java.util.Stack;

class 큰_수_만들기 {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            int num = number.charAt(i) - '0';

            while (k > 0 && !stack.isEmpty() && stack.peek() < num) {
                stack.pop();
                k--;
            }
            stack.push(num);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        int size = stack.size();

        for (int i = 0; i < size; i++) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}