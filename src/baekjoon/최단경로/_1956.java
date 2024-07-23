package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1956 {

    private static int[][] distance;
    private static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(str.nextToken());
        int e = Integer.parseInt(str.nextToken());

        distance = new int[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i != j)
                    distance[i][j] = INF;
            }
        }

        for (int i = 0; i < e; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            int c = Integer.parseInt(str.nextToken());

            distance[a-1][b-1] = c;
        }

        floyd(v);
        System.out.println(findResult(v));
    }

    private static void floyd(int v) {
        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                if (i == k)
                    continue;
                for (int j = 0; j < v; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }

    private static int findResult(int v) {
        int answer = INF;

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j)
                    continue;
                if (distance[i][j] != INF && distance[j][i] != INF)
                    answer = Math.min(answer, distance[i][j] + distance[j][i]);
            }
        }

        return answer;
    }
}
