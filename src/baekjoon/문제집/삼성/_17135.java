package baekjoon.문제집.삼성;

// 궁수들은 격자판 바로 아래 행.
// 3명의 궁수가 위치 가능 (완전탐색을 통해 궁수 위치 지정)
// 궁수 범위 (d) 안에서 가장 가까운 적 처리. 여러개라면 가장 왼쪽.
// 궁수들은 한명에게 다 쏠 수 있으니 체크를 쏘는 적의 idx 저장해두고 마지막에 처리하기
// 매 턴마다 맵 전체를 순회하며 적이 아직 있는지 확인

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _17135 {
    private static int[][] map;
    private static int[][] copyMap;
    private static boolean[] archer;
    private static int max = 0;
    private static int n;
    private static int m;
    private static int d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        d = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        archer = new boolean[m];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(max);
    }

    private static void dfs(int depth, int start) {
        if (depth == 3) {
            copyMap = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    copyMap[i][j] = map[i][j];
            }
            int count = run();
            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < m; i++) {
            archer[i] = true;
            dfs(depth + 1, i + 1);
            archer[i] = false;
        }
    }

    private static int run() {
        int count = 0;
        while (true) {
            List<Idx> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (archer[i]) {
                    bfs(i, list);
                }
            }

            // list로 적들 인덱스 위치 없애고 count
            for (Idx idx: list) {
                copyMap[idx.x][idx.y] = 0;
                count++;
            }

            // 맵 한칸씩 내리기
            move();

            // 존재하는지 확인
            if (!mapCheck())
                break;
        }

        return count;
    }

    private static void bfs(int archerIdx, List<Idx> list) {
        Queue<int[]> queue = new LinkedList<>();

        if (copyMap[n - 1][archerIdx] == 1) {
            if (!list.contains(new Idx(n - 1, archerIdx)))
                list.add(new Idx(n - 1, archerIdx));
            return;
        } else {
            queue.add(new int[] {n - 1, archerIdx, 1});
        }

        boolean[][] visited = new boolean[n][m];
        visited[n - 1][archerIdx] = true;

        int[] dx = {0, -1, 0};    // 아래는 안가도 됨.
        int[] dy = {-1, 0, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int count = now[2];

            if (count == d)
                return;

            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || ny >= m || visited[nx][ny])
                    continue;

                if (copyMap[nx][ny] == 1) {
                    if (!list.contains(new Idx(nx, ny)))
                        list.add(new Idx(nx, ny));
                    return;
                }

                queue.add(new int[] {nx, ny, count + 1});
                visited[nx][ny] = true;
            }
        }
    }

    private static void move() {
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j > 0; j--) {
                copyMap[j][i] = copyMap[j - 1][i];
            }
        }

        for (int i = 0; i < m; i++)
            copyMap[0][i] = 0;
    }

    private static boolean mapCheck() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 1)
                    return true;
            }
        }

        return false;
    }
}

class Idx {
    int x;
    int y;

    Idx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Idx i = (Idx) o;
        return x == i.x && y == i.y;
    }
}