package baekjoon.DFS_BFS;

// bfs. 방문 위치 visited
// 하나의 반복문에서 방향 4개 변경 큐 삽입, 변경된 방향으로의 앞으로 1칸 이동 큐 삽입

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1726 {
    private static int[][] map;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        str = new StringTokenizer(br.readLine(), " ");
        int startX = Integer.parseInt(str.nextToken());
        int startY = Integer.parseInt(str.nextToken());
        int startDir = Integer.parseInt(str.nextToken());

        str = new StringTokenizer(br.readLine(), " ");
        int destX = Integer.parseInt(str.nextToken());
        int destY = Integer.parseInt(str.nextToken());
        int destDir = Integer.parseInt(str.nextToken());

        System.out.println(bfs(n, m, startX, startY, startDir, destX, destY, destDir));
    }

    private static int bfs(int n, int m, int startX, int startY, int startDir, int destX, int destY, int destDir) {
        if (startX == destX && startY == destY && startDir == destDir)
            return 0;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n + 1][m + 1][4];

        int[] d = {1, 3, 2, 4};
        int transDir = 0;
        for (int i = 0; i < 4; i++) {
            if (d[i] == startDir)
                transDir = i;
        }

        queue.add(new int[] {startX, startY, transDir, 0});    // ~,~,~, 카운트
        visited[startX][startY][startDir - 1] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int dir = now[2];
            int count = now[3];

            int nx = x;
            int ny = y;

            for (int i = 1; i <= 3; i++) {
                nx += dx[d[dir] - 1];
                ny += dy[d[dir] - 1];

                if (nx < 1 || ny < 1 || nx > n || ny > m || map[nx][ny] == 1) break;

                if (visited[nx][ny][d[dir] - 1]) continue;

                if (nx == destX && ny == destY && d[dir] == destDir)
                    return count + 1;

                visited[nx][ny][d[dir] - 1] = true;
                queue.add(new int[] {nx, ny, dir, count + 1});
            }

            for (int i = 1; i <= 3; i++) {
                int nextDir = (dir + i) % 4;
                int tempX = x + dx[d[nextDir] - 1];
                int tempY = y + dy[d[nextDir] - 1];

                if (x == destX && y == destY && d[nextDir] == destDir)
                    return count + ((i == 3) ? 1 : i);

                if (tempX < 1 || tempY < 1 || tempX > n || tempY > m || visited[tempX][tempY][d[nextDir] - 1] || map[tempX][tempY] == 1)
                    continue;

                queue.add(new int[] {x, y, nextDir, count + ((i == 3) ? 1 : i)});
            }
        }

        return -1;
    }
}