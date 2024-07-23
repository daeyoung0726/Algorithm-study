package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _11404 {

    private static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        StringTokenizer str;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            int c = Integer.parseInt(str.nextToken());

            if (distance[a-1][b-1] > c)
                distance[a-1][b-1] = c;
        }

        floyd(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distance[i][j] == Integer.MAX_VALUE / 2)
                    sb.append(0 + " ");
                else
                    sb.append(distance[i][j] + " ");
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    private static void floyd(int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i == k)
                    continue;
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }
}