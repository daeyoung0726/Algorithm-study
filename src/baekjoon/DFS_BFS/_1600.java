package baekjoon.DFS_BFS;

// bfs이용. visited 3차원 배열 이용 [x][y][k] k : 말처럼 이동 가능 횟수
// bfs는 한 번에 2번 탐색. (말처럼 + 상하좌우)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1600 {
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int w = Integer.parseInt(str.nextToken());
        int h = Integer.parseInt(str.nextToken());

        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        System.out.println(bfs(w, h, k));
    }

    private static int bfs(int w, int h, int k) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[h][w][k + 1];

        queue.add(new int[] {0, 0, 0, 0});    // 행, 열, k, count
        visited[0][0][0] = true;

        int[] horseX = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] horseY = {-1, 1, -2, 2, -2, 2, -1, 1};
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int kk = now[2];
            int count = now[3];

            if (x == h - 1 && y == w - 1)
                return count;

            if (kk < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + horseX[i];
                    int ny = y + horseY[i];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny][kk + 1] || map[nx][ny] == 1)
                        continue;

                    queue.add(new int[] {nx, ny, kk + 1, count + 1});
                    visited[nx][ny][kk + 1] = true;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny][kk] || map[nx][ny] == 1)
                    continue;

                queue.add(new int[] {nx, ny, kk, count + 1});
                visited[nx][ny][kk] = true;
            }
        }

        return -1;
    }
}