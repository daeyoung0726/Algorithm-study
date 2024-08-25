package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _18353 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] map = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(str.nextToken());
        }

        System.out.println(n - dp(map, n));
    }

    private static int dp(int[] map, int n) {
        int[] d = new int[n];
        int max = Integer.MIN_VALUE;

        d[0] = 1;
        for (int i = 1; i < n; i++) {
            d[i] = 1;
            for (int j = 0; j < i; j++) {
                if (map[j] > map[i]) {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
        }

        for (int x: d) {
            max = Math.max(max, x);
        }

        return max;
    }
}