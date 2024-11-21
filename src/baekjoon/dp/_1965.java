package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _1965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        System.out.println(dp(arr, n));
    }

    private static int dp(int[] arr, int n) {

        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
        }

        int max = -1;

        for (int val : d)
            max = Math.max(max, val);

        return max;
    }
}