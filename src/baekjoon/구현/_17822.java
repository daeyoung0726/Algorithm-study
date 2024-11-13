package baekjoon.구현;

// 돌린 후 인접한지 모두 다 확인 (양 옆, 인점 원판 같은 인덱스)
// map으로 저장한 후, bfs를 통해서 주변 같은 값 확인. (다른 점은, 범위를 나간다면 끝으로 가야함)
// bfs 넣기 전에, 주변에 같은 값이 있는지 확인한 후 넣기
// bfs를 한번이라도 실행한다면, boolean 체크
// boolean이 false라면 모든 값 더해서 평균낸 후, 평균보다 크면 -1 작으면 +1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _17822 {

    private static int[][] circle;
    private static int n;
    private static int m;
    private static boolean[][] visited;

    private static final int[] dx = { -1, 0, 1, 0 };
    private static final int[] dy = { 0, -1, 0 ,1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        int t = Integer.parseInt(str.nextToken());

        circle = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                circle[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        boolean isSame;
        for (int i = 0; i < t; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int d = Integer.parseInt(str.nextToken());
            int k = Integer.parseInt(str.nextToken());

            visited = new boolean[n][m];
            rollingCircle(x, d, k);

            isSame = false;
            for (int j = 0; j < n; j++) {
                for (int z = 0; z < m; z++) {
                    if (circle[j][z] != 0 && !visited[j][z] && isExists(j, z, circle[j][z])) {
                        bfs(j, z);
                        isSame = true;
                    }
                }
            }

            if (!isSame) {
                int sum = 0;
                int count = 0;
                for (int j = 0; j < n; j++) {
                    for (int z = 0; z < m; z++) {
                        if (circle[j][z] != 0) {
                            sum += circle[j][z];
                            count++;
                        }
                    }
                }

                double avg = (double) sum / count;

                for (int j = 0; j < n; j++) {
                    for (int z = 0; z < m; z++) {

                        if (circle[j][z] != 0) {

                            if (circle[j][z] > avg) {
                                circle[j][z] -= 1;
                            } else if (circle[j][z] < avg) {
                                circle[j][z] += 1;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(circleSum());

    }

    private static void rollingCircle(int x, int d, int k) {
        for (int i = x; i <= n; i += x) {
            if (d == 0) {
                for (int z = 0; z < k; z++) {
                    int temp = circle[i-1][m-1];
                    for (int j = m - 1; j > 0; j--) {
                        circle[i-1][j] = circle[i-1][j-1];
                    }
                    circle[i-1][0] = temp;
                }
            }

            if (d == 1) {
                for (int z = 0; z < k; z++) {
                    int temp = circle[i-1][0];
                    for (int j = 0; j < m - 1; j++) {
                        circle[i-1][j] = circle[i-1][j+1];
                    }
                    circle[i-1][m-1] = temp;
                }
            }
        }
    }

    private static boolean isExists(int i, int j, int num) {
        if (i + 1 < n && circle[i + 1][j] == num) {
            return true;
        }
        if (i - 1 >= 0 && circle[i-1][j] == num) {
            return true;
        }

        if (circle[i][(j + 1) % m] == num) {
            return true;
        }
        if (circle[i][(j - 1 < 0) ? (m-1) : (j-1)] == num) {
            return true;
        }
        return false;
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {x, y});
        visited[x][y] = true;

        int num = circle[x][y];

        circle[x][y] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (ny >= m)
                    ny = 0;
                if (ny < 0)
                    ny = m - 1;

                if (nx >= 0 && nx < n && circle[nx][ny] == num) {
                    circle[nx][ny] = 0;
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static int circleSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += circle[i][j];
            }
        }

        return sum;
    }
}