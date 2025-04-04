package programmers;

public class 정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + triangle[i+1][j+1]);
            }
        }

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        return answer;
    }
}
