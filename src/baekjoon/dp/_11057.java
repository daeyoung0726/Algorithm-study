package baekjoon.dp;

// 1자리면 10, 2자리면 00 ~ 99 까지 10, 9, 8~ 1 = 55
// 3자리면 2자리까지 한걸 기억해서 차례대로.
// 즉. 이차원 배열로 만들어서 0 ~ 9까지 각 가질 수 있느 값 넣고, n자리 구할 때, n-1자리 idx이상의 값들을 더해서 넣기

import java.util.Scanner;

public class _11057 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] dp = new int[n + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                int sum = 0;
                for (int k = j; k < 10; k++) {
                    sum += dp[i - 1][k];
                }
                dp[i][j] = sum % 10007;
            }
        }

        int sum = 0;
        for (int x: dp[n])
            sum += x;

        System.out.println(sum % 10007);
    }
}