package baekjoon.문제집.삼성;

// 한 열, 한 행씩 모두 돈다.
// 처음 갈때 부터 count를 한다. 높이가 낮아지면 카운트를 다시 한다. 높아지면, 카운트가 l 이상이라면 계속 이어지도록.
// 높이가 달라질 때마다 카운트는 계속 처음부터.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14890 {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int l = Integer.parseInt(str.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (rowWay(i, l, n)) {
                count++;
            }
            if (colWay(i, l, n)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean rowWay(int row, int l, int n) {
        int count = 1;

        for (int i = 0; i < n - 1; i++) {

            int now = map[row][i];
            int next = map[row][i + 1];
            if (now == next) {
                count++;
            } else if (now - next > 0) {
                if (now - next == 1) {
                    int c = 1;
                    for (int j = i + 2; j < n; j++) {
                        if (next != map[row][j]) {
                            if (next + 1 == map[row][j]) {
                                if (c < 2 * l)
                                    return false;
                            }
                            break;
                        }
                        c++;
                    }

                    if (c < l) {
                        return false;
                    }

                } else {
                    return false;
                }
            } else {
                if (next - now == 1) {
                    if (count >= l) {
                        count = 1;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean colWay(int col, int l, int n) {
        int count = 1;

        for (int i = 0; i < n - 1; i++) {

            int now = map[i][col];
            int next = map[i + 1][col];
            if (now == next) {
                count++;
            } else if (now - next > 0) {
                if (now - next == 1) {
                    int c = 1;
                    for (int j = i + 2; j < n; j++) {
                        if (next != map[j][col]) {
                            if (next + 1 == map[j][col]) {
                                if (c < 2 * l)
                                    return false;
                            }
                            break;
                        }
                        c++;
                    }

                    if (c < l) {
                        return false;
                    }

                } else {
                    return false;
                }
            } else {
                if (next - now == 1) {
                    if (count >= l) {
                        count = 1;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}