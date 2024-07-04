package baekjoon;

import java.util.Scanner;

public class _2447 {
    private static char[][] star;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        star = new char[N][N];

        recursive(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(star[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    private static void recursive(int x, int y, int n, boolean blank) {

        if (blank) {
            for (int i = x; i < x+n; i++) {
                for (int j = y; j < y+n; j++)
                    star[i][j] = ' ';
            }
            return;
        }

        if (n == 1) {
            star[x][y] = '*';
            return;
        }

        int m = n / 3;

        for (int i = x; i < x+n; i += m) {
            for (int j = y; j < y+n; j += m) {
                if (i == x+m && j == y+m)
                    recursive(i, j, m, true);
                else
                    recursive(i, j, m, false);
            }
        }
    }

}