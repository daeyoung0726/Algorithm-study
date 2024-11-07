package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] p = new int[n + 1];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(str.nextToken());
        }

        int[] dp = new int[n + 1];

        // dp[i] = Math.max(dp[i], dp[i - pi] + p[pi])

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + p[j]);
            }
        }

        System.out.println(dp[n]);
    }
}