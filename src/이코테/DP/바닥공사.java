package 이코테.DP;

import java.util.Scanner;

public class 바닥공사 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(dp(n));
    }

    private static int dp(int n) {
        int[] d = new int[n+1];

        d[1] = 1;
        d[2] = 3;

        for (int i = 3; i <= n; i++) {
            d[i] = (d[i-1] + 2*d[i-2]) % 796796;    // 2를 곱해주는 이유는  i-2는 2가지 경우가 있기 때문.
        }

        return d[n];
    }
}
