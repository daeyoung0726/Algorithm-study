package programmers.고득점kit.DFS_BFS;

import java.util.*;

class 아이템_줍기 {

    private int[][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        map = new int[101][101];

        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    if (j == x1 || j == x2 || k == y1 || k == y2) {
                        if (map[k][j] != -1) {
                            map[k][j] = 1;
                        }
                    } else {
                        map[k][j] = -1;
                    }
                }
            }


        }
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> queue = new LinkedList<>();

        boolean[][] visited = new boolean[101][101];
        visited[characterY][characterX] = true;
        queue.add(new int[] {characterX, characterY, 0});

        int min = Integer.MAX_VALUE;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        int nx, ny;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];
            int count = now[2];

            if (x == itemX && y == itemY) {
                min = Math.min(min, count / 2);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx > 0 && ny > 0 && nx <= 100 && ny <= 100) {
                    if (!visited[ny][nx] && map[ny][nx] == 1) {
                        visited[ny][nx] = true;
                        queue.add(new int[] {nx, ny, count + 1});
                    }
                }
            }
        }

        return min;
    }
}