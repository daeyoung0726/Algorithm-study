package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int s = Integer.parseInt(str.nextToken());

        int[] arr = new int[n];

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        System.out.println(dp(arr, n, s));
    }

    private static int dp(int[] arr, int n, int s) {
        int min = Integer.MAX_VALUE;

        int sum = arr[0];
        int start = 0;

        if (sum >= s)
            return 1;

        for (int i = 1; i < n; i++) {
            sum += arr[i];

            int x = start;
            for (int j = x; j < i; j++) {
                if (sum - arr[j] >= s) {
                    sum -= arr[j];
                    start += 1;
                } else
                    break;
            }

            if (sum >= s) {
                min = Math.min(min, i - start + 1);
            }
        }

        if (min == Integer.MAX_VALUE)
            return 0;
        return min;
    }
}