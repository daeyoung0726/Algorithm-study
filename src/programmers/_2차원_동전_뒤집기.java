package programmers;

public class _2차원_동전_뒤집기 {
    private int[][] beginning;
    private int[][] target;

    private int answer = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        this.beginning = beginning;
        this.target = target;

        int n = beginning.length;
        int m = beginning[0].length;
        dfs(n, m, 0, 0, 0);
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }

    private void dfs(int n, int m, int count, int x, int y) {
        if (check(n, m)) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = y; i < n; i++) {
            reverse(m, i, 1);
            dfs(n, m, count + 1, x, i + 1);
            reverse(m, i, 1);
        }

        for (int i = x; i < m; i++) {
            if (checkCol(n, i)) {
                reverse(n, i, 0);
                dfs(n, m, count + 1, i + 1, y);
                reverse(n, i, 0);
            }
        }
    }

    private void reverse(int n, int num, int type) {
        if (type == 1) {
            for (int i = 0; i < n; i++) {
                beginning[num][i] = (beginning[num][i] == 1) ? 0 : 1;
            }
        }

        if (type == 0) {
            for (int i = 0; i < n; i++) {
                beginning[i][num] = (beginning[i][num] == 1) ? 0 : 1;
            }
        }
    }

    private boolean check(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (beginning[i][j] != target[i][j])
                    return false;
            }
        }

        return true;
    }

    private boolean checkCol(int n, int x) {
        for (int i = 0; i < n; i++) {
            if (beginning[i][x] == target[i][x])
                return false;
        }
        return true;
    }
}