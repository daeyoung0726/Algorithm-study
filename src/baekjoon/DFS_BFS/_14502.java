package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _14502 {

    private static int[][] map;
    private static int n;
    private static int m;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(max);
    }

    // 벽 세우기
    private static void dfs(int x, int start) {
        if (start == 3) {
            bfs();
            return;
        }

        for (int i = x; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(i, start+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    // 전염 시키기
    private static void bfs() {
        Queue<Point3> queue = new LinkedList<>();

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        int[][] cloneMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cloneMap[i][j] = map[i][j];
                if (map[i][j] == 2)
                    queue.add(new Point3(i, j));
            }
        }


        while (!queue.isEmpty()) {
            Point3 now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && cloneMap[nx][ny] == 0) {
                    cloneMap[nx][ny] = 2;
                    queue.add(new Point3(nx, ny));
                }
            }
        }
        maxUpdate(cloneMap);
    }

    // 최대 개수 업데이트
    private static void maxUpdate(int[][] cloneMap) {

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cloneMap[i][j] == 0)
                    count++;
            }
        }

        max = Math.max(max, count);
    }
}

class Point3 {
    int x;
    int y;

    Point3(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
