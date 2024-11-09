package baekjoon.구현;

// 주사위 경우의 수
//   원본       동       서        남        북
//    1        1        1        4        2
//  5 2 6    4 5 2    2 6 4    5 1 6    5 3 6
//    3        3        3        2        4
//    4        6        5        3        1

// 동쪽 이동 시, idx 0, 2 같음. a[1] = a[4] && a[3] = a[5] && a[4] = a[3] && a[5] = a[1]
// 서쪽 이동 시, idx 0, 2 같음. a[1] = a[5] && a[3] = a[4] && a[4] = a[1] && a[5] = a[3]
// 남쪽 이동 시, idx 4, 5 같음. a[0] = a[3] && a[1] = a[0] && a[2] = a[1] && a[3] = a[2]
// 북쪽 이동 시, idx 4, 5 같음. a[0] = a[1] && a[1] = a[2] && a[2] = a[3] && a[3] = a[0]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14499 {

    private static int[] dice = { 0, 0, 0, 0, 0, 0 };
    private static int[][] map;
    private static int[] cmd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int diceX = Integer.parseInt(str.nextToken());
        int diceY = Integer.parseInt(str.nextToken());

        int k = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        cmd = new int[k];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            cmd[i] = Integer.parseInt(str.nextToken());
        }

        System.out.println(cmdDice(n, m, k, diceX, diceY));
    }

    private static String cmdDice(int n, int m, int k, int x, int y) {

        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { 1, -1, 0, 0 };

        StringBuilder sb = new StringBuilder();

        int nx, ny;
        for (int i = 0; i < k; i++) {
            int dir = cmd[i] - 1;
            nx = x + dx[dir];
            ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            roll(dir);

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[3];
            } else {
                dice[3] = map[nx][ny];
                map[nx][ny] = 0;
            }

            sb.append(dice[1]).append('\n');
            x = nx;
            y = ny;
        }

        return sb.toString();

    }

    private static void roll(int dir) {
        int temp = dice[1];

        switch (dir) {
            case 0: {
                dice[1] = dice[4];
                dice[4] = dice[3];
                dice[3] = dice[5];
                dice[5] = temp;
                break;
            }
            case 1: {
                dice[1] = dice[5];
                dice[5] = dice[3];
                dice[3] = dice[4];
                dice[4] = temp;
                break;
            }
            case 2: {
                dice[1] = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[2];
                dice[2] = temp;
                break;
            }
            case 3: {
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = dice[0];
                dice[0] = temp;
            }
        }
    }
}