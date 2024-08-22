package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _18405 {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        str = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(str.nextToken());
        int x = Integer.parseInt(str.nextToken());
        int y = Integer.parseInt(str.nextToken());

        System.out.println(bfs(n, s, x, y));
    }

    private static int bfs(int n, int s, int x, int y) {
        PriorityQueue<Point5> pq = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time)
                return a.num - b.num;
            return a.time - b.time;
        });

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    pq.add(new Point5(i, j, map[i][j], 0));
                }
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        int time = 0;
        while (!pq.isEmpty()) {
            Point5 now = pq.poll();

            if (s == now.time)
                return map[x-1][y-1];
            if (time == now.time)
                time++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == 0) {
                    map[nx][ny] = now.num;
                    pq.add(new Point5(nx, ny, map[nx][ny], time));
                }
            }

        }

        return map[x-1][y-1];
    }
}

class Point5 {
    int x;
    int y;
    int num;
    int time;

    Point5(int x, int y, int num, int time) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.time = time;
    }
}