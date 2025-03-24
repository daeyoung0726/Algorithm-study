package programmers;

import java.util.*;

public class 지게차와_크레인 {
    private char[][] map;
    private int count = 0;

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();

        map = new char[n + 2][m + 2];


        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                if (i == 0 || i == n + 1 || j == 0 || j == m + 1)
                    map[i][j] = 'a';
                else
                    map[i][j] = storage[i - 1].charAt(j - 1);
            }
        }

        for (int i = 0; i < requests.length; i++) {
            String s = requests[i];

            if (s.length() == 1) {
                bfs(n, m, s.charAt(0));
            } else {
                allDelete(n, m, s.charAt(0));
            }
        }
        return n * m - count;
    }

    private void bfs(int n, int m, char c) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n + 2][m + 2];

        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n + 2 || ny >= m + 2 || visited[nx][ny])
                    continue;

                if (map[nx][ny] == c) {
                    count++;
                    map[nx][ny] = 'a';
                    visited[nx][ny] = true;
                } else if (map[nx][ny] == 'a') {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    private void allDelete(int n, int m, char c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i + 1][j + 1] == c) {
                    map[i + 1][j + 1] = 'a';
                    count++;
                }
            }
        }
    }
}