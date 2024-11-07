package baekjoon.dp;

// 1 : 1
// 2 : 10
// 3 : 100, 101
// 4 : 1000, 1001, 1010
// 5 : 10000, 10001, 10010, 10100, 10101
// 6 : 100000, 100001, 100010, 100100, 100101, 101000, 101001, 101010  피보나치 수열

import java.util.Scanner;

public class _2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[] dp = new long[n + 1];

        if (n == 1) {
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }
}