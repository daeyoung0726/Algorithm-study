package programmers;

// dfs.

import java.util.*;

public class 비밀_코드_해독 {
    private List<Integer> list;
    private int answer = 0;
    private int[][] q;
    private int[] ans;

    public int solution(int n, int[][] q, int[] ans) {
        list = new ArrayList<>();
        this.q = q;
        this.ans = ans; 

        dfs(n, 0, 1);
        return answer;
    }

    private void dfs(int n, int count, int x) {
        if (count == 5) {
            if (check())
                answer++;
            return;
        }

        for (int i = x; i <= n; i++) {
            list.add(i);
            dfs(n, count + 1, i + 1);
            list.remove((Integer) i);
        }
    }

    private boolean check() {
        for (int i = 0; i < q.length; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (q[i][j] == list.get(k)) {
                        count++;
                        continue;
                    }
                }
            }
            if (count != ans[i])
                return false;
        }
        return true;
    }
}