package programmers.dp;

import java.util.Arrays;

class 거스름돈 {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];

        Arrays.sort(money);

        dp[0] = 1;

        for (int i = 0; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                dp[j] += dp[j - money[i]];
            }
        }
        return dp[n];
    }
}