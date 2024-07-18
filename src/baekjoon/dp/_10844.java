package baekjoon.dp;

import java.util.*;

public class _10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(dp(n));
    }

    private static long dp(int n) {
        long[][] d = new long[n+1][10];

        for (int i = 1; i < 10; i++) {
            d[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j < 10; j++) {
                if (j == 0)
                    d[i][j] = d[i-1][1] % 1000000000;

                else if (j == 9)
                    d[i][j] = d[i-1][8] % 1000000000;

                else
                    d[i][j] = (d[i - 1][j - 1] + d[i - 1][j + 1]) % 1000000000;
            }
        }
        long sum = 0;
        for (long val: d[n])
            sum += val;
        return sum % 1000000000;
    }
}