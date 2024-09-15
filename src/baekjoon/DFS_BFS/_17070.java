package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _17070 {

    private static int[][] map;
    private static int n;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        result = 0;

        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        dfs(0, 1, 0);

        System.out.println(result);

    }

    private static void dfs(int x, int y, int direction) {
        if (x == n-1 && y == n-1) {
            result++;
            return;
        }
        switch(direction) {
            case 0:     // 가로
                if (y + 1 < n && map[x][y+1] == 0) {
                    dfs(x, y+1, 0);
                }
                break;
            case 1:     // 세로
                if (x + 1 < n && map[x+1][y] == 0) {
                    dfs(x+1, y, 1);
                }
                break;
            case 2:     // 대각선
                if (y + 1 < n && map[x][y+1] == 0) {
                    dfs(x, y+1, 0);
                }
                if (x + 1 < n && map[x+1][y] == 0) {
                    dfs(x+1, y, 1);
                }
                break;
        }

        if (x + 1 < n && y + 1 < n && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x+1][y+1] == 0)
            dfs(x+1, y+1, 2);
    }
}