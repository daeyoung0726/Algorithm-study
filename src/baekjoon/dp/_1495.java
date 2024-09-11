package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int s = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int[] vol = new int[n+1];
        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            vol[i] = Integer.parseInt(str.nextToken());
        }

        boolean[][] dp = new boolean[n+1][m+1];

        dp[0][s] = true;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= m; j++) {
                if (dp[i-1][j]) {
                    int minVol = j - vol[i];
                    int maxVol = j + vol[i];

                    if (minVol >= 0)
                        dp[i][minVol] = true;
                    if (maxVol <= m)
                        dp[i][maxVol] = true;
                }
            }
        }

        int answer = -1;
        for (int i = m; i >= 0; i--) {
            if (dp[n][i]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}