package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];
        int k;
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            k = 0;
            st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()) {
                arr[i][k++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {    // dp 마지막 행 값 저장.
            dp[n-1][i] = arr[n-1][i];
        }

        // 마지막 이전부터 초기화 시작.
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + arr[i][j];
            }
        }

        System.out.println(dp[0][0]);

    }
}
