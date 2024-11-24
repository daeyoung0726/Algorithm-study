package baekjoon.문제집.삼성;

// bfs로 확산을 시키는데, 확산을 시키는 것은 새로운 map에 저장한다.
// 원래 미세먼지 값은, 계산 한 후, 확산된 map에 대한 값들을 원래 map으로 옮긴다.
// 공기청정기를 통해서 위 -1은 반시계, 아래 -1은 시계 방향으로 크게 한칸씩 옮긴다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _17144 {

    private static int map[][];
    private static int r;
    private static int c;
    private static int airFresher[] = new int[2];

    private static final int[] dx = { -1, 0, 1, 0 };
    private static final int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(str.nextToken());
        c = Integer.parseInt(str.nextToken());
        int t = Integer.parseInt(str.nextToken());

        map = new int[r][c];

        int x = 0;
        for (int i = 0; i < r; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(str.nextToken());
                map[i][j] = num;
                if (num == -1) {
                    airFresher[x++] = i;
                }
            }
        }

        for (int i = 0; i < t; i++) {
            bfs(); // 확산시키기
            moveAir(); // 공기 청정하기
        }

        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        int[][] cloneMap = new int[r][c];
        int count;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            count = 0;

            int num = map[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c || map[nx][ny] == -1)
                    continue;

                count++;
                cloneMap[nx][ny] += num / 5;
            }

            map[x][y] -= (num / 5) * count;
        }

        calcMap(cloneMap);
    }

    private static void calcMap(int[][] cloneMap) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += cloneMap[i][j];
            }
        }
    }

    private static void moveAir() {
        upAirFresher();
        downAirFresher();
    }

    private static void upAirFresher() {
        int x = airFresher[0];

        /*
                   2
            1            3
            (공기)  4
           순서대로 계산
        */

        for (int i = x - 1; i > 0; i--) {    // x - 1인 이유는, 공기청정기에 들어오면 없어지기에
            map[i][0] = map[i - 1][0];
        }

        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }

        for (int i = 0; i < x; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }

        for (int i = c - 1; i > 1; i--) {    // i > 1 인 이유는, 공기청정기는 안건들이기 위해.
            map[x][i] = map[x][i - 1];
        }

        map[x][1] = 0;    // 공기 나왔기에 0으로 초기화.
    }

    private static void downAirFresher() {
        int x = airFresher[1];

        /*
            (공기)  4
            1            3
                   2
           순서대로 계산
        */

        for (int i = x + 1; i < r - 1; i++) {    // x + 1인 이유는, 공기청정기에 들어오면 없어지기에
            map[i][0] = map[i + 1][0];
        }

        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }

        for (int i = r - 1; i > x; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }

        for (int i = c - 1; i > 1; i--) {    // i > 1 인 이유는, 공기청정기는 안건들이기 위해.
            map[x][i] = map[x][i - 1];
        }

        map[x][1] = 0;    // 공기 나왔기에 0으로 초기화.
    }
}