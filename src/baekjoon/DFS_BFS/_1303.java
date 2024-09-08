package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1303 {

    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());    // 가로. 열
        int m = Integer.parseInt(str.nextToken());    // 세로. 행

        map = new char[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int wCount = 0;
        int bCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    char x = map[i][j];
                    int count = bfs(i, j, n, m);

                    if (x == 'W')
                        wCount += count * count;
                    if (x == 'B')
                        bCount += count * count;
                }
            }
        }

        System.out.println(wCount + " " + bCount);

    }

    private static int bfs(int x, int y, int n, int m) {
        Queue<Point12> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.add(new Point12(x, y));
        int count = 0;
        while (!queue.isEmpty()) {
            Point12 now = queue.poll();

            count++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    if (!visited[nx][ny] && map[nx][ny] == map[x][y]) {
                        visited[nx][ny] = true;
                        queue.add(new Point12(nx, ny));
                    }
                }
            }
        }

        return count;
    }
}

class Point12 {
    int x;
    int y;

    Point12(int x, int y) {
        this.x = x;
        this.y = y;
    }
}