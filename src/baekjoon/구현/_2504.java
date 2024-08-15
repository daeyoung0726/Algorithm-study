package baekjoon.구현;

import java.util.*;

public class _2504 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        String s = sc.next();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int temp = 0;

            if (c == '(') {
                stack.push(-2);
            }
            if (c == '[') {
                stack.push(-3);
            }
            else {
                if (c == ')') {
                    while (true) {
                        if (stack.isEmpty()) {
                            System.out.println(0);
                            return;
                        }
                        int pre = stack.pop();
                        if (pre == -2) {
                            temp = (temp == 0) ? 2 : temp * 2;
                            stack.push(temp);
                            break;
                        } else if (pre == -3) {
                            System.out.println(0);
                            return;
                        } else {
                            temp += pre;
                        }
                    }
                }
                if (c == ']') {
                    while (true) {
                        if (stack.isEmpty()) {
                            System.out.println(0);
                            return;
                        }
                        int pre = stack.pop();

                        if (pre == -3) {
                            temp = (temp == 0) ? 3 : temp * 3;
                            stack.push(temp);
                            break;
                        } else if (pre == -2) {
                            System.out.println(0);
                            return;
                        } else {
                            temp += pre;
                        }
                    }
                }
            }
        }
        int total = 0;
        while (!stack.isEmpty()) {
            int value = stack.pop();
            if (value == -2 || value == -3) {
                System.out.println(0);
                return;
            }
            total += value;
        }

        System.out.println(total);
    }
}