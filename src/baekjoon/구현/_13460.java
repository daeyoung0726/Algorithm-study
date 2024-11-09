package baekjoon.구현;

// 상, 하, 좌, 우 로 기울이고, 한 번 기울이면 #을 만날 때 까지 옮김.
// 만약, 빨, 파 구슬이 같은 곳으로 향하여 부딪치는 경우, 미리 기록 후에 -1 하여 움직임.
// 동시에 들어가면 -1 출력
// visited를 통해서 이미 왔던 길은 안가야함. 간다면, 못푸는 문제임

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _13460 {

    private static char[][] map;
    private static boolean[][][][] visited;

    private static final int[] dx = { -1, 0 , 1, 0 };
    private static final int[] dy = { 0, -1, 0 , 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][n][m];

        int redX = 0, redY = 0;
        int blueX = 0, blueY = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);

                if (s.charAt(j) == 'R') {
                    redX = i;
                    redY = j;
                }

                if (s.charAt(j) == 'B') {
                    blueX = i;
                    blueY = j;
                }
            }
        }

        System.out.println(bfs(redX, redY, blueX, blueY));
        // bfs 실행
    }

    private static int bfs(int redX, int redY, int blueX, int blueY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {redX, redY, blueX, blueY, 0});  // 빨강, 파랑 좌표와 이동 횟수
        visited[redX][redY][blueX][blueY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int rx = current[0], ry = current[1];
            int bx = current[2], by = current[3];
            int moves = current[4];

            if (moves >= 10) return -1;  // 10번 이상이면 실패

            for (int i = 0; i < 4; i++) {
                // 구슬을 한 방향으로 굴림
                int[] redResult = move(rx, ry, dx[i], dy[i]);
                int[] blueResult = move(bx, by, dx[i], dy[i]);

                int newRx = redResult[0], newRy = redResult[1];
                int newBx = blueResult[0], newBy = blueResult[1];

                boolean redHole = redResult[2] == 1;
                boolean blueHole = blueResult[2] == 1;

                // 파란 구슬이 구멍에 빠지면 실패
                if (blueHole) continue;

                // 빨간 구슬만 구멍에 빠지면 성공
                if (redHole && !blueHole) return moves + 1;

                // 빨강, 파랑이 같은 위치에 도착하면
                if (newRx == newBx && newRy == newBy) {
                    if (redResult[3] > blueResult[3]) {
                        newRx -= dx[i];
                        newRy -= dy[i];
                    } else {
                        newBx -= dx[i];
                        newBy -= dy[i];
                    }
                }

                // 아직 방문하지 않은 상태라면 큐에 추가
                if (!visited[newRx][newRy][newBx][newBy]) {
                    visited[newRx][newRy][newBx][newBy] = true;
                    queue.add(new int[] {newRx, newRy, newBx, newBy, moves + 1});
                }
            }
        }

        return -1;
    }

    // 구슬을 움직이는 함수
    private static int[] move(int x, int y, int dx, int dy) {
        int distance = 0;
        boolean hole = false;

        while (map[x + dx][y + dy] != '#' && map[x][y] != 'O') {
            x += dx;
            y += dy;
            distance++;

            if (map[x][y] == 'O') {
                hole = true;
                break;
            }
        }

        return new int[] {x, y, hole ? 1 : 0, distance};  // 좌표, 구멍에 빠졌는지 여부, 이동 거리
    }
}