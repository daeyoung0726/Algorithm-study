package baekjoon.문제집.코테대비;

// 색이 다르면 변경
// 행, 따로 움직이며 최대 값 구하기.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _3085 {

    private static char[][] map;

    private static int max = Integer.MIN_VALUE;
    private static int[] dx = { 1, 0 };    // 남, 동만 확인
    private static int[] dy = { 0, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        swapMap(n);
        System.out.println(max);
    }

    private static void swapMap(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                        char temp = map[i][j];
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = temp;

                        calcRow(i, n);
                        calcRow(nx, n);

                        calcCol(j, n);
                        calcCol(ny, n);

                        map[nx][ny] = map[i][j];
                        map[i][j] = temp;
                    }
                }
            }
        }
    }

    private static void calcRow(int i, int n) {
        int count = 1;
        char pre = map[i][0];

        for (int j = 1; j < n; j++) {
            if (pre == map[i][j])
                count++;
            else {
                max = Math.max(max, count);
                count = 1;
                pre = map[i][j];
            }
        }
        max = Math.max(max, count);
    }

    private static void calcCol(int i, int n) {
        int count = 1;
        char pre = map[0][i];

        for (int j = 1; j < n; j++) {
            if (pre == map[j][i])
                count++;
            else {
                max = Math.max(max, count);
                count = 1;
                pre = map[j][i];
            }
        }
        max = Math.max(max, count);
    }
}