package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp(num)).append('\n');
        }

        System.out.println(sb);
    }

    private static int dp(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 1;

        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= num; j++) {
                dp[j] += dp[j - i];
            }
        }

        return dp[num];
    }
}