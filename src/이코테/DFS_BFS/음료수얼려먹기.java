package 이코테.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 음료수얼려먹기 {

    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for(int j = 0; j < s.length(); j++)
                arr[i][j] = s.charAt(j) - '0';
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j, N, M))
                    count++;
            }
        }
        System.out.println(count);
    }

    private static boolean dfs(int x, int y, int N, int M) {
        if (x < 0 || y < 0 || x >= N || y >= M)
            return false;

        if (arr[x][y] == 0) {
            arr[x][y] = 1;
            dfs(x-1, y, N, M);
            dfs(x+1, y, N, M);
            dfs(x, y-1, N, M);
            dfs(x, y+1, N, M);

            return true;
        } else
            return false;
    }
}
