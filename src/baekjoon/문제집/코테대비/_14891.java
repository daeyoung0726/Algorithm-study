package baekjoon.문제집.코테대비;

// 가로면 true, 세로면 false로 구분
// 인덱스 순차적으로 접근. 만약, 세로 길이를 넘어가면, sum한 후에 return


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14891 {

    private static int[][] map;
    private static boolean[][] visited;

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
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int x, int y) {

        if (y == m) {
            dfs(x + 1, 0);
            return;
        }

        if (x == n) {
            sum();
            return;
        }


        visited[x][y] = true;    // 가로로 연결
        dfs(x, y + 1);

        visited[x][y] = false;    // 세로로 연결
        dfs(x, y + 1);
    }

    private static void sum() {
        int result = 0;

        int cum;

        for (int i = 0; i < n; i++) {        // 가로 합
            cum = 0;
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    cum *= 10;
                    cum += map[i][j];
                } else {
                    result += cum;
                    cum = 0;
                }
            }
            result += cum;
        }

        for (int i = 0; i < m; i++) {        // 세로 합
            cum = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j][i]) {
                    cum *= 10;
                    cum += map[j][i];
                } else {
                    result += cum;
                    cum = 0;
                }
            }
            result += cum;
        }

        max = Math.max(max, result);
    }
}