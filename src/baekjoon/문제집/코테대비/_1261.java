package baekjoon.문제집.코테대비;

// 상, 하, 좌, 우 이동 bfs 작성.
// 빈 방이라고 그냥 이동 (이동한 곳은 더 이상 방문 못하게 visited로 기록)
// 만약, 벽이 보인다면, 부수고 이동.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1261 {

    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(str.nextToken());
        int n = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs(n, m));
    }

    private static int bfs(int n, int m) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[] {0, 0, 0});    // 행, 열, 벽 부순 개수

        visited[0][0] = true;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int breakWall = now[2];

            if (x == n - 1 && y == m - 1) {
                return breakWall;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    if (map[nx][ny] == 1)
                        queue.add(new int[] {nx, ny, breakWall + 1});
                    else
                        queue.add(new int[] {nx, ny, breakWall});
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }
}