package baekjoon.dp;

import java.util.Scanner;

public class _1904 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(dp(n));
    }

    private static int dp(int n) {
        int[] arr = new int[n];
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        arr[0] = 1;
        arr[1] = 2;
        for(int i = 2; i < n; i++) {
            arr[i] = (arr[i-1] + arr[i-2]) % 15746;
        }
        return arr[n-1];
    }
}
