package baekjoon.문제집.대기업_코테_나오는_유형_모음;

import java.util.Scanner;

public class _9655 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String name = (n % 2 == 1) ? "SK" : "CY";

        System.out.println(name);
    }
}
/*
public class _9655 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n+1];

        if (n >= 1)
            dp[1] = 1;
        if (n >= 2)
            dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            int x = dp[i-1];

            if (dp[i] == 0) {
                dp[i] = (x == 1) ? 2 : 1;
                if (i+2 <= n)
                    dp[i+2] = (x == 1) ? 2 : 1;
            }
        }

        String name = (dp[n] == 1) ? "SK" : "CY";
        System.out.println(name);
    }
}*/
