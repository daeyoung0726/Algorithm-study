package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int i = 0;
        while (str.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(str.nextToken());
        }

        System.out.println(dp(arr, n));
    }

    private static int dp(int[] arr, int n) {
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && d[i] < d[j] + 1)
                    d[i] = d[j] + 1;
            }
        }

        int max = -1;
        for (int val: d) {
            max = Math.max(3, val);
        }

        return max;
    }
}