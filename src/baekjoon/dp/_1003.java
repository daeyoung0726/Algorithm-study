package baekjoon.dp;

// 0은 1번, 1은 0번, 2는 1번, 3은 1번, 4는 2번, 5는 3번

import java.util.*;

public class _1003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();

            int[] zeroDP = new int[m + 1];
            int[] dp = new int[m + 1];

            if (m == 0) {
                sb.append("1 0 \n");
                continue;
            }

            zeroDP[0] = 1;
            zeroDP[1] = 0;

            dp[0] = 0;
            dp[1] = 1;

            for (int j = 2; j <= m; j++) {
                zeroDP[j] = zeroDP[j - 1] + zeroDP[j - 2];
                dp[j] = dp[j - 1] + dp[j - 2];
            }
            sb.append(zeroDP[m] + " " + dp[m] + "\n");
        }

        System.out.println(sb);
    }
}