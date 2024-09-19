package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + 1;

            if (i > 3) {
                dp[i] = Math.max(dp[i], dp[i-3]*2);
            }
        }

        System.out.println(dp[n]);
    }
}