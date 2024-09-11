package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16197 {

    private static char[][] map;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        map = new char[n][m];

        List<int[]> coins = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'o') {
                    coins.add(new int[] { i, j });
                }
            }
        }

        // BFS 실행
        System.out.println(bfs(coins));
    }

    private static int bfs(List<int[]> coins) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];

        // 동전 두 개의 초기 위치 큐에 추가
        int x1 = coins.get(0)[0], y1 = coins.get(0)[1];
        int x2 = coins.get(1)[0], y2 = coins.get(1)[1];

        queue.add(new int[] { x1, y1, x2, y2 });
        visited[x1][y1][x2][y2] = true;

        // 이동 방향 (상, 좌, 하, 우)
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        int count = 0;

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int[] current = queue.poll();
                x1 = current[0];
                y1 = current[1];
                x2 = current[2];
                y2 = current[3];

                // 동전 중 하나만 떨어진 경우 성공
                for (int i = 0; i < 4; i++) {
                    int nx1 = x1 + dx[i], ny1 = y1 + dy[i];
                    int nx2 = x2 + dx[i], ny2 = y2 + dy[i];

                    // 각 동전이 밖으로 나가는지 체크
                    boolean firstOut = isOut(nx1, ny1);
                    boolean secondOut = isOut(nx2, ny2);

                    if (firstOut && !secondOut || !firstOut && secondOut) {
                        if (count + 1 > 10)
                            return -1;
                        return count + 1;  // 하나만 나가면 성공
                    }

                    if (firstOut && secondOut) {
                        continue;  // 둘 다 나가면 무시
                    }

                    // 벽에 부딪히면 제자리에
                    if (map[nx1][ny1] == '#') {
                        nx1 = x1;
                        ny1 = y1;
                    }

                    if (map[nx2][ny2] == '#') {
                        nx2 = x2;
                        ny2 = y2;
                    }

                    // 두 동전이 같은 위치에 있을 수 없음
                    if (nx1 == nx2 && ny1 == ny2) {
                        continue;
                    }

                    // 이미 방문한 상태가 아니면 큐에 추가
                    if (!visited[nx1][ny1][nx2][ny2]) {
                        visited[nx1][ny1][nx2][ny2] = true;
                        queue.add(new int[] { nx1, ny1, nx2, ny2 });
                    }
                }
            }

            count++;

            // 10번 이상 버튼을 누른 경우 -1 리턴
            if (count > 10) {
                return -1;
            }
        }

        return -1;  // 10번 안에 해결되지 않으면 -1 반환
    }

    // 보드 바깥으로 나갔는지 체크하는 함수
    private static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
