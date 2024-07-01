package baekjoon;

import java.util.Stack;
import java.util.Scanner;

public class _28278 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int select = sc.nextInt();

            switch(select) {
                case 1 :
                    int num = sc.nextInt();
                    stack.push(num);
                    break;
                case 2:
                    if (!stack.isEmpty())
                        sb.append(stack.pop()).append('\n');
                    else
                        sb.append(-1).append('\n');
                    break;
                case 3:
                    sb.append(stack.size()).append('\n');
                    break;
                case 4:
                    int result = stack.isEmpty() ? 1 : 0;
                    sb.append(result).append('\n');
                    break;
                case 5:
                    if (!stack.isEmpty())
                        sb.append(stack.peek()).append('\n');
                    else
                        sb.append(-1).append('\n');
            }
        }
        System.out.println(sb);
    }
}