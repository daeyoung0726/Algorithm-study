package baekjoon.dp;

import java.util.Scanner;

public class _24416 {
    private static int recurCount = 0;
    private static int dpCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        /* 시간 확인 추가 */
        long startFibo = System.currentTimeMillis();
        recurFibo(n);
        long endFibo = System.currentTimeMillis();

        long startDp = System.currentTimeMillis();
        dpFibo(n);
        long endDp = System.currentTimeMillis();

        System.out.println("재귀 시간 : " + (endFibo - startFibo));
        System.out.println("DP 시간 : " + (endDp - startDp));
        System.out.println(recurCount + " " + dpCount);
    }

    private static long recurFibo(int n) {
        if(n <= 2) {
            recurCount++;
            return 1;
        }
        else
            return recurFibo(n-1) + recurFibo(n-2);
    }

    private static long dpFibo(int n) {
        int[] f = new int[n];
        f[0] = f[1] = 1;

        for(int i = 2; i < n; i++) {
            dpCount++;
            f[i] = f[i-1] + f[i-2];
        }
        return f[n-1];
    }
}
