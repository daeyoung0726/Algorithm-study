package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1932 {

    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        StringTokenizer str;
        int j;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            j = 0;
            while (str.hasMoreTokens()) {
                arr[i][j++] = Integer.parseInt(str.nextToken());
            }
        }

        System.out.println(dp(n));
    }

    private static int dp(int n) {
        int max = Integer.MIN_VALUE;
        int[][] dpMap = new int[n][n];
        dpMap[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                dpMap[i][j] = Math.max(dpMap[i-1][j] + arr[i][j], dpMap[i][j]);
                dpMap[i][j+1] = Math.max(dpMap[i-1][j] + arr[i][j+1], dpMap[i][j+1]);
            }
        }

        for (int x: dpMap[n-1]) {
            max = Math.max(max, x);
        }
        return max;
    }
}
