package baekjoon.문제집.코테대비;
// dp 사용
// 1일떄, 2일때, 3일떄 따로

import java.util.Scanner;

public class _9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int j = 4; j <= 10; j++) {
            dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
        }

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();

            sb.append(dp[num]).append('\n');
        }

        System.out.println(sb);
    }
}