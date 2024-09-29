package 이코테.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 금광 {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(str.nextToken());
            int col = Integer.parseInt(str.nextToken());

            map = new int[row][col];

            str = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    map[j][k] = Integer.parseInt(str.nextToken());
                }
            }

            sb.append(dp(row, col)).append('\n');

        }

        System.out.println(sb);
    }

    private static int dp(int row, int col) {

        int max = Integer.MIN_VALUE;
        int[][] cloneMap = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cloneMap[i][j] = map[i][j];
            }
        }

        for (int i = 1; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (j == 0) {
                    cloneMap[j][i] += Math.max(cloneMap[j][i-1], cloneMap[j+1][i-1]);
                } else if (j == row-1) {
                    cloneMap[j][i] += Math.max(cloneMap[j-1][i-1], cloneMap[j][i-1]);
                } else {
                    cloneMap[j][i] += Math.max(cloneMap[j][i-1], Math.max(cloneMap[j-1][i-1], cloneMap[j+1][i-1]));
                }
            }
        }

        for (int i = 0; i < row; i++) {
            max = Math.max(max, cloneMap[i][col-1]);
        }
        return max;
    }
}
