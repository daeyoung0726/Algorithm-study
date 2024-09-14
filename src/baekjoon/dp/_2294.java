package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];

        for (int i = 1; i < k + 1; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
        }


        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], 1 + dp[j - coin[i]]);

            }
        }
        /*for (int i = 0; i < n; i++) {
            if (k >= coin[i])
                dp[coin[i]] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                if (dp[j] == 0) {
                    dp[j] = dp[j - coin[i]] == 0 ? 0 : 1 + dp[j - coin[i]];
                } else {
                    if (dp[j - coin[i]] != 0)
                        dp[j] = Math.min(dp[j], 1 + dp[j - coin[i]]);
                }
            }
        }*/

        if (dp[k] == 0)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
    }
}