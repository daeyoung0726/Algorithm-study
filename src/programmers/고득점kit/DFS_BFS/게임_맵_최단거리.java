package programmers.고득점kit.DFS_BFS;

import java.util.Queue;
import java.util.LinkedList;

class 게임_맵_최단거리 {
    public int solution(int[][] maps) {

        return bfs(maps, maps.length, maps[0].length);
    }

    private int bfs(int[][] maps, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {0, 0});

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        int nx, ny;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = maps[x][y] + 1;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        return (maps[n - 1][m - 1] == 1) ? -1 : maps[n - 1][m - 1];
    }
}