package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] days = new int[n+1];
        int[] moneys = new int[n+1];

        StringTokenizer str;
        for (int i = 1; i <= n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            days[i] = Integer.parseInt(str.nextToken());
            moneys[i] = Integer.parseInt(str.nextToken());
        }

        int[] dp = new int[n+2];

        for (int i = 1; i <= n; i++) {
            int day = days[i];
            int money = moneys[i];

            if (i + day <= n+1) {
                dp[i + day] = Math.max(dp[i + day], dp[i] + money);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }

        System.out.println(dp[n+1]);
    }
}