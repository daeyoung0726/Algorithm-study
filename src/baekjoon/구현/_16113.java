package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _16113 {

    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int col = n / 5;
        map = new boolean[5][col + 2];
        int r = 0;
        int c = 0;

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);

            if (c == col) {
                r++;
                c = 0;
            }
            if (ch == '#')
                map[r][c] = true;
            c++;
        }

        System.out.println(parsingNum());
    }

    private static String parsingNum() {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (int x = 0; x < map[0].length - 2; x++) {
            if (map[0][x]) {
                i = x;
                break;
            }
        }
        while (i < map[0].length - 2) {
            if (!map[2][i+1] && map[4][i+1]) {    // 0
                sb.append(0);
                i = nextCol(i+2);
                continue;
            }

            if (map[4][i] && !map[0][i+1]) {    // 1
                sb.append(1);
                i = nextCol(i);    // 1은 1열만 차지
                continue;
            }

            if (!map[1][i] && !map[3][i+2]) {    // 2
                sb.append(2);
                i = nextCol(i+2);
                continue;
            }

            if (!map[1][i] && map[2][i] && !map[3][i]) {    // 3
                sb.append(3);
                i = nextCol(i+2);
                continue;
            }

            if (!map[0][i+1]) {    // 4
                sb.append(4);
                i = nextCol(i+2);
                continue;
            }

            if (!map[3][i] && !map[1][i+2]) {    // 5
                sb.append(5);
                i = nextCol(i+2);
                continue;
            }

            if (map[3][i] && !map[1][i+2]) {    // 6
                sb.append(6);
                i = nextCol(i+2);
                continue;
            }

            if (!map[2][i+1] && !map[4][i+1]) {    // 7
                sb.append(7);
                i = nextCol(i+2);
                continue;
            }

            if (map[1][i] && map[3][i] && map[2][i+1] && map[1][i+2] && map[3][i+2]) {    // 8
                sb.append(8);
                i = nextCol(i+2);
                continue;
            }

            if (map[1][i] && !map[3][i] && map[1][i+2]) {    // 9
                sb.append(9);
                i = nextCol(i+2);
                continue;
            }
        }

        return sb.toString();
    }

    private static int nextCol(int idx) {
        for (int i = idx + 1; i < map[0].length; i++) {
            if (map[0][i])
                return i;
        }
        return Integer.MAX_VALUE;
    }
}