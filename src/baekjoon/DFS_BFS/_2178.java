package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2178 {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(dfs(0, 0, n, m));

    }

    private static int dfs(int x, int y, int n, int m) {
        Queue<Point13> queue = new LinkedList<>();

        queue.add(new Point13(x, y));

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        while (!queue.isEmpty()) {
            Point13 now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 1) {
                        map[nx][ny] = map[now.x][now.y] + 1;
                        queue.add(new Point13(nx, ny));
                    }
                }
            }
        }

        return map[n-1][m-1];
    }
}

class Point13 {
    int x;
    int y;

    Point13(int x, int y) {
        this.x = x;
        this.y = y;
    }
}