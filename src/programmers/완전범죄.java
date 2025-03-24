package programmers;

public class 완전범죄 {
    public int solution(int[][] info, int n, int m) {

        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int[] now : info) {
            int traceA = now[0];
            int traceB = now[1];

            boolean[][] next = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!dp[i][j]) continue;

                    if (i + traceA < n)
                        next[i + traceA][j] = true;

                    if (j + traceB < m)
                        next[i][j + traceB] = true;
                }
            }

            dp = next;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j]) {
                    return i;
                }
            }
        }
        return -1;
    }
}