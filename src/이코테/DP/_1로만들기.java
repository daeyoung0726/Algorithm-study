package 이코테.DP;

import java.util.Scanner;

public class _1로만들기 {

    private static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();

        arr = new int[x+1];

        System.out.println(dp(x));
    }

    private static int dp(int x) {

        for (int i = 2; i <= x; i++) {
            arr[i] = arr[i-1] + 1;

            if (i % 2 == 0)
                arr[i] = Math.min(arr[i], arr[i/2] + 1);

            if (i % 3 == 0)
                arr[i] = Math.min(arr[i], arr[i/3] + 1);

            if (i % 5 == 0)
                arr[i] = Math.min(arr[i], arr[i/5] + 1);
        }

        return arr[x];
    }
}
