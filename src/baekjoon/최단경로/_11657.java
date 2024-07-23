package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _11657 {

    private static ArrayList<ArrayList<Point1>> graph = new ArrayList<>();
    private static long[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        distance = new long[n+1];

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            int c = Integer.parseInt(str.nextToken());

            graph.get(a).add(new Point1(b, c));
        }

        boolean check = BellmanFord(1, n);

        StringBuilder sb = new StringBuilder();
        if (!check) {
            System.out.println(-1);
        } else {
            for (int i = 2; i < n + 1; i++) {
                if (distance[i] >= Long.MAX_VALUE / 2)
                    sb.append(-1).append('\n');
                else
                    sb.append(distance[i]).append('\n');
            }
        }

        System.out.println(sb);
    }

    private static boolean BellmanFord(int start, int n) {

        for (int i = 1; i < n+1; i++) {
            distance[i] = Long.MAX_VALUE / 2;
        }

        distance[start] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[j] == Long.MAX_VALUE / 2 )
                    continue;
                for (Point1 p : graph.get(j)) { // distance[j] != Long.MAX_VALUE / 2  => j에 도달할 수 없는 상태
                    if (distance[p.getX()] > distance[j] + p.getY()) {
                        distance[p.getX()] = distance[j] + p.getY();
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (distance[i] == Long.MAX_VALUE / 2 )
                continue;
            for (Point1 p : graph.get(i)) {
                if (distance[p.getX()] > distance[i] + p.getY()) {
                    return false;
                }
            }
        }

        return true;
    }
}

class Point1{
    private int x;
    private int y;

    Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

}