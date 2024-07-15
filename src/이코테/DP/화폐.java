package 이코테.DP;

import java.util.Arrays;
import java.util.Scanner;

public class 화폐 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println(dp(arr, m));
    }

    private static int dp(int[] arr, int m) {
        int size = arr.length;

        int[] d = new int[m+1];
        Arrays.fill(d, 10001);
        d[0] = 0;

        for (int i = 0; i < size; i++) {
            for (int j = arr[i]; j <= m; j++) {
                d[j] = Math.min(d[j], d[j-arr[i]] + 1);
            }
        }

        if (d[m] == 10001)
            return -1;
        return d[m];
    }
}
