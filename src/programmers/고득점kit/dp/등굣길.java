package programmers.고득점kit.dp;

class 등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[n][m];
        boolean[][] notAccess = new boolean[n][m];

        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0] - 1;
            int y = puddles[i][1] - 1;
            notAccess[y][x] = true;
        }

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (!notAccess[i][j]) {
                    if (i - 1 >= 0 && !notAccess[i-1][j]) {
                        dp[i][j] += dp[i - 1][j] % 1000000007;
                    }
                    if (j - 1 >= 0 && !notAccess[i][j-1]) {
                        dp[i][j] += dp[i][j-1] % 1000000007;
                    }
                }
            }
        }

        return dp[n-1][m-1] % 1000000007;
    }
}