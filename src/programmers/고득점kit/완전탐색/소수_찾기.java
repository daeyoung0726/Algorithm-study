package programmers.고득점kit.완전탐색;

import java.util.*;

class 소수_찾기 {

    private List<Integer> list;
    private int[] num;
    private boolean[] visited;

    public int solution(String numbers) {
        int len= numbers.length();
        num = new int[len];
        visited = new boolean[len];

        for (int i = 0; i < len; i++) {
            num[i] = numbers.charAt(i) - '0';
        }
        list = new ArrayList<>();

        dfs(0, 0);
        return list.size();
    }

    private void dfs(int n, int idx) {
        if (idx == num.length) {
            if (check(n))
                list.add(n);
            return;
        }

        if (check(n))
            list.add(n);

        int x;
        for (int i = 0; i < num.length; i++) {
            if (n == 0 && num[i] == 0)
                continue;

            if (!visited[i]) {
                x = 10 * n + num[i];
                visited[i] = true;
                dfs(x, idx + 1);
                visited[i] = false;
            }
        }
    }

    private boolean check(int n) {
        return !isPrime(n) && !list.contains(n);
    }

    private boolean isPrime(int n) {

        if (n < 2)
            return true;
        if (n == 2)
            return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return true;
        }
        return false;
    }
}