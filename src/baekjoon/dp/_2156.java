package baekjoon.dp;

import java.util.Scanner;

public class _2156 {

    private static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(dp(n));
    }

    private static int dp(int n) {


        if (n == 1)
            return arr[0];
        if (n == 2)
            return arr[0] + arr[1];

        int[] d = new int[n];
        d[0] = arr[0];
        d[1] = arr[0] + arr[1];
        d[2] = Math.max(d[1], Math.max(arr[0] + arr[2], arr[1] + arr[2]));

        for (int i = 3; i < n; i++) {
            d[i] = Math.max(d[i - 1], Math.max(d[i - 3] + arr[i - 1] + arr[i], d[i - 2] + arr[i]));
        }


        return d[n - 1];
    }

}