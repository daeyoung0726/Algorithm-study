package baekjoon.구현;

// 5개 중 하나이다.
// 4개는 한 쪽 방향으로 이동하기 때문에 dfs로 간단히 구현 가능하다.
// ㅜ모양은 중간에서 방향을 나눠야 한다. 중간 depth에서 해당 좌표를 또 넘긴다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14500 {

    private static int[][] map;
    private static boolean[][] visited;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };
    private static int n;
    private static int m;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void dfs(int x, int y, int depth, int sum) {

        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isPossible(nx, ny)) {

                if (depth == 2) {
                    visited[nx][ny] = true;
                    dfs(x, y, depth + 1, sum + map[nx][ny]);
                    visited[nx][ny] = false;
                }

                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    private static boolean isPossible(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && !visited[x][y];
    }
}