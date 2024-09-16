package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1743 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < k; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(str.nextToken());
            int c = Integer.parseInt(str.nextToken());

            map[r-1][c-1] = 1;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int count = bfs(i, j);
                    max = Math.max(max, count);
                }
            }
        }

        System.out.println(max);
    }

    private static int bfs(int x, int y) {
        Queue<Point14> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.add(new Point14(x, y));

        int count = 0;

        while (!queue.isEmpty()) {
            Point14 now = queue.poll();

            count++;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 1) {
                    if (!visited[nx][ny]) {
                        queue.add(new Point14(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return count;
    }
}

class Point14 {
    int x;
    int y;

    Point14(int x, int y) {
        this.x = x;
        this.y = y;
    }
}