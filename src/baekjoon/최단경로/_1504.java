package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _1504 {
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");


        int n = Integer.parseInt(str.nextToken());
        int e = Integer.parseInt(str.nextToken());

        arr = new int[n+1][n+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++)
                if (i == j) {
                    arr[i][j] = 0;
                } else
                    arr[i][j] = 100000000;
        }

        for (int i = 0; i < e; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());
            int w = Integer.parseInt(str.nextToken());

            arr[u][v] = w;
            arr[v][u] = w;
        }
        str = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(str.nextToken());
        int y = Integer.parseInt(str.nextToken());

        System.out.println(floyd(x, y, n));
    }

    private static int floyd(int x, int y, int n) {

        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                if (k == i)
                    continue;
                for (int j = 1; j < n+1; j++) {
                    if (arr[i][k] != 100000000 && arr[k][j] != 100000000) {
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }
        }
        int answer = Math.min(arr[1][x] + arr[x][y] + arr[y][n], arr[1][y] + arr[y][x] + arr[x][n]);

        if (answer >= 100000000)
            return -1;
        return answer;
    }
}