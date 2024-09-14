package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3085 {

    private static String[][] map;
    private static int n;
    private static int max = Integer.MIN_VALUE;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new String[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = String.valueOf(str.charAt(j));
            }
        }

        for (int i = 0; i < n; i++) {
            findCol(i);
            findRow(i);
        }
        visitMap(0, 0);
        System.out.println(max);

    }

    private static void visitMap(int x, int y) {

        for (int i = x; i < n; i++) {
            for (int j = y; j < n; j++) {

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                        if (!map[i][j].equals(map[nx][ny])) {
                            String temp = map[i][j];
                            map[i][j] = map[nx][ny];
                            map[nx][ny] = temp;
                            findRow(i);
                            findCol(j);

                            if (max == n)
                                return;

                            map[nx][ny] = map[i][j];
                            map[i][j] = temp;
                        }
                    }
                }
            }
        }


    }

    private static void findRow(int x) {
        int count = 1;
        int m = 0;

        for (int i = 1; i < n; i++) {
            String now = map[x][i];

            if (map[x][i-1].equals(now)) {
                count++;
                m = Math.max(m, count);
            } else {
                count = 1;
            }
        }

        max = Math.max(max, m);
    }

    private static void findCol(int x) {
        int count = 1;
        int m = 0;

        for (int i = 1; i < n; i++) {
            String now = map[i][x];

            if (map[i-1][x].equals(now)) {
                count++;
                m = Math.max(m, count);
            } else {
                count = 1;
            }
        }

        max = Math.max(max, m);
    }
}