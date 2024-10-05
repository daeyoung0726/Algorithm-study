package programmers.dfs_bfs;

import java.util.Stack;

class 괄호변환 {

    private StringBuilder sb = new StringBuilder();

    public String solution(String p) {
        if (p.equals("")) return "";

        dfs(p);

        return sb.toString();
    }

    private void dfs(String w) {

        if (w.equals(""))
            return;

        int balance = 0;
        int i = 0;

        for (; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance == 0) {
                break;
            }
        }

        String u = w.substring(0, i + 1);
        String v = w.substring(i + 1);

        if (checkTrue(u)) {
            sb.append(u);
            dfs(v);
        } else {
            sb.append('(');
            dfs(v);
            sb.append(')');
            sb.append(reverse(u.substring(1, u.length() - 1)));
        }

    }

    private boolean checkTrue(String u) {
        if (u.charAt(0) == ')')
            return false;

        Stack<Integer> stack = new Stack<>();

        int size = u.length();

        for (int i = 0; i < size; i++) {
            if (u.charAt(i) == '(')
                stack.push(-1);
            else {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private String reverse(String u) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < u.length(); i++) {
            char c = u.charAt(i);
            if (c == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }

        return sb.toString();
    }
}