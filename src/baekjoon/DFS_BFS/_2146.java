package baekjoon.DFS_BFS;

// bfs를 통해 상, 하, 좌, 우 중 하나라도 0인 곳이 있다면 그 것을 다시 bfs 호출
// 큐에 들어간 값들을 통해서 다시 다른 구역으로 가는 가장 빠른 곳을 찾기
// 한 구역이 끝날 때 까지 visited는 유지해야함.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2146 {
    private static int[][] map;
    private static boolean[][] visited;

    private static int min = Integer.MAX_VALUE;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];
        StringTokenizer str;

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j, n);
                }
            }
        }

        System.out.println(min);
    }

    private static void bfs(int i, int j, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});

        visited[i][j] = true;

        List<int[]> idxs = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny])
                    continue;

                if (map[nx][ny] == 0) {
                    idxs.add(new int[] {nx, ny});
                } else {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        bridge(idxs, n);
    }

    private static void bridge(List<int[]> idxs, int n) {
        Queue<int[]> queue = new LinkedList<>();

        boolean[][] copyVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyVisited[i][j] = visited[i][j];
            }
        }

        for (int[] nn: idxs) {
            queue.add(new int[] {nn[0], nn[1], 0});
            copyVisited[nn[0]][nn[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int count = now[2];

            if (map[now[0]][now[1]] == 1) {
                min = Math.min(min, count);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || copyVisited[nx][ny])
                    continue;

                queue.add(new int[] {nx, ny, count + 1});
                copyVisited[nx][ny] = true;

            }
        }
    }
}