package baekjoon.DFS_BFS;

// 홀수 : 낮, 짝수 : 밤

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16933 {
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs(n, m, k));
    }

    private static int bfs(int n, int m, int k) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][k + 1];

        queue.add(new int[] {0, 0, 0, 1});    // x, y, k, count
        visited[0][0][0] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int kk = now[2];
            int count = now[3];

            if (x == n - 1 && y == m - 1) {
                return count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if (map[nx][ny] == 1 && kk < k && !visited[nx][ny][kk + 1]) {
                    if (count % 2 == 0) {
                        queue.add(new int[] {x, y, kk, count + 1});
                    } else {
                        queue.add(new int[] {nx, ny, kk + 1, count + 1});
                        visited[nx][ny][kk + 1] = true;
                    }
                } else if (map[nx][ny] == 0 && !visited[nx][ny][kk]) {
                    visited[nx][ny][kk] = true;
                    queue.add(new int[] {nx, ny, kk, count + 1});
                }
            }
        }

        return -1;
    }
}