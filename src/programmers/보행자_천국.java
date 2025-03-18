package programmers;

// dp 3차원 배열. 마지막에 0, 1로 위에서, 왼쪽에서 오는거 처리.
// 현재 위치가 1이라면 넘기기. 이전 위치가 2라면 오는 방향에 대해서만 처리.

public class 보행자_천국 {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];

        // 초기 값 설정. 둘 다 1하면 2로 시작함. 한쪽 방향에 대해서만 1로 처리
        dp[0][0][0] = 1;
        dp[0][0][1] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 && j == 0) || cityMap[i][j] == 1)
                    continue;

                if (i - 1 >= 0) {
                    if (cityMap[i - 1][j] == 2) {
                        dp[i][j][0] = dp[i - 1][j][0] % MOD;
                    } else {
                        dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                    }
                }

                if (j - 1 >= 0) {
                    if (cityMap[i][j - 1] == 2) {
                        dp[i][j][1] = dp[i][j - 1][1] % MOD;
                    } else {
                        dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                    }
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}
