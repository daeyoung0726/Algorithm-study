package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _17086 {

    private static int[][] map;
    private static int[] dx = { -1, 0, 1, 0, -1, -1, 1, 1 };
    private static int[] dy = { 0, -1, 0, 1, -1, 1, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int safetyDis = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (map[i][j] == 0) {
                    int count = bfs(i, j, n, m);
                    safetyDis = Math.max(safetyDis, count);
                }
            }
        }

        System.out.println(safetyDis);

    }

    private static int bfs(int i, int j, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        visited[i][j] = true;
        queue.add(new int[] { i, j, 0 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int count = now[2];

            if (map[x][y] == 1)
                return count;

            for (int k = 0; k < 8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny, count + 1 });
                }
            }
        }
        return -1;
    }
}