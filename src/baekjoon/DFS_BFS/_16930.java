package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16930 {

    private static char[][] map;
    private static int[][] visited;
    private static int n;
    private static int m;
    private static int k;

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        k = Integer.parseInt(str.nextToken());

        map = new char[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                visited[i][j] = Integer.MAX_VALUE;
        }

        str = new StringTokenizer(br.readLine(), " ");
        int x1 = Integer.parseInt(str.nextToken());
        int y1 = Integer.parseInt(str.nextToken());
        int x2 = Integer.parseInt(str.nextToken());
        int y2 = Integer.parseInt(str.nextToken());

        System.out.println(bfs(x1, y1, x2, y2));
    }

    private static int bfs(int x1, int y1, int x2, int y2) {
        Queue<int[]> queue = new LinkedList<>();

        visited[x1-1][y1-1] = 0;
        queue.add(new int[] { x1, y1 });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            if (x == x2 && y == y2) {
                return visited[x-1][y-1];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                for (int j = 0; j < k; j++) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 1 || ny < 1 || nx > n || ny > m || map[nx-1][ny-1] == '#')
                        break;

                    // 이미 해당 좌표를 더 적은 횟수로 방문한 경우 패스. 이미 더 작으면, 그 작은 수로 상 하 좌 우 움직였기 때문.
                    if (visited[nx-1][ny-1] <= visited[x-1][y-1]) {
                        break;
                    }

                    // 처음 방문하거나 더 적은 횟수로 방문하는 경우
                    if (visited[nx-1][ny-1] == Integer.MAX_VALUE) {
                        visited[nx-1][ny-1] = visited[x-1][y-1] + 1;
                        queue.add(new int[] { nx, ny });
                    }

                }

            }
        }
        return -1;
    }
}