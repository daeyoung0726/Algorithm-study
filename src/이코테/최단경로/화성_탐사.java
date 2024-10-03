package 이코테.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 화성_탐사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer str;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];

            for (int j = 0; j < n; j++) {
                str = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < n; k++) {
                    map[j][k] = Integer.parseInt(str.nextToken());
                }
            }

            sb.append(dijkstra(map, n)).append('\n');

        }

        System.out.println(sb);
    }

    private static int dijkstra(int[][] map, int n) {
        PriorityQueue<Point1> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);

        int[][] distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        distance[0][0] = map[0][0];
        pq.add(new Point1(0, 0, map[0][0]));

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        while (!pq.isEmpty()) {
            Point1 p = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (distance[nx][ny] > map[nx][ny] + p.dis) {
                        distance[nx][ny] = map[nx][ny] + p.dis;
                        pq.add(new Point1(nx, ny, distance[nx][ny]));
                    }
                }
            }

        }

        return distance[n-1][n-1];
    }
}

class Point1 {
    int x;
    int y;
    int dis;

    Point1(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}
