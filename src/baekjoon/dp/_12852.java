package baekjoon.dp;

import java.util.Scanner;

public class _12852 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        int[] temp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            temp[i] = 1;

            if (i % 2 == 0) {
                if (dp[i] > dp[i / 2] + 1) {
                    dp[i] = dp[i / 2] + 1;
                    temp[i] = 2;
                }
            }

            if (i % 3 == 0) {
                if (dp[i] > dp[i / 3] + 1) {
                    dp[i] = dp[i / 3] + 1;
                    temp[i] = 3;
                }
            }
        }
        System.out.println(dp[n]);

        StringBuilder sb = new StringBuilder();
        sb.append(n + " ");
        while (n > 1) {
            if (temp[n] == 1) {
                n -= 1;
            } else {
                n /= temp[n];
            }

            sb.append(n + " ");
        }


        System.out.println(sb);
    }
}