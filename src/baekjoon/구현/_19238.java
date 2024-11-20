package baekjoon.구현;

// PriorityQueue를 통해 거리가 같다면 행 우선, 그것도 같다면 열
// 만약 사람을 queue에서 꺼내면, queue를 초기화, visited도 초기화.
// 해당 목적지 이동. 목적지 도착 후, 연료를 이동거리의 * 2 충전. 반복. 이동 중 연료 없으면 끝

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _19238 {
    private static int[][] map;
    private static Set<Index> humanIdx;
    private static Set<Index> desIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        str = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(str.nextToken()) - 1;
        int startY = Integer.parseInt(str.nextToken()) - 1;

        humanIdx = new HashSet<>();
        desIdx = new HashSet<>();

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine());
            int humanX = Integer.parseInt(str.nextToken()) - 1;
            int humanY = Integer.parseInt(str.nextToken()) - 1;

            int desX = Integer.parseInt(str.nextToken()) - 1;
            int desY = Integer.parseInt(str.nextToken()) - 1;

            humanIdx.add(new Index(i, humanX, humanY));
            desIdx.add(new Index(i, desX, desY));
        }


        System.out.println(bfs(startX, startY, n, k, m));
    }

    private static int bfs(int startX, int startY, int n, int k, int m) {
        Comparator<int[]> cmp = (a, b) -> {
            if (a[3] == b[3]) {
                if (a[0] == b[0])
                    return a[1] - b[1];
                return a[0] - b[0];
            }
            return a[3] - b[3];
        };

        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp);    // x, y, count, 상태(0:손님 x, 1: 손님 o)

        boolean[][] visited = new boolean[n][n];

        boolean check = false;

        for (int i = 0; i < m; i++) {
            if (humanIdx.contains(new Index(i, startX, startY))) {
                pq.add(new int[] {startX, startY, i, 0, 1});
                humanIdx.remove(new Index(i, startX, startY));
                check = true;
                break;
            }
        }

        if (!check)
            pq.add(new int[] {startX, startY, -1, 0, 0});

        visited[startX][startY] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int x = now[0];
            int y = now[1];
            int idx = now[2];
            int count = now[3];
            int state = now[4];

            if (k < count)
                return -1;

            boolean isTrue = false;
            if (state == 0) {
                for (int i = 0; i < m; i++) {
                    if (humanIdx.contains(new Index(i, x, y))) {
                        k -= count;
                        pq.clear();
                        pq.add(new int[] {x, y, i, 0, 1});
                        humanIdx.remove(new Index(i, x, y));
                        visited = new boolean[n][n];
                        visited[x][y] = true;
                        isTrue = true;
                        break;
                    }
                }
            }

            if (isTrue)
                continue;

            if (state == 1 && desIdx.contains(new Index(idx, x, y))) {

                k += count;
                pq.clear();
                pq.add(new int[] {x, y, -1, 0, 0});
                desIdx.remove(new Index(idx, x, y));
                visited = new boolean[n][n];
                visited[x][y] = true;
                continue;
            }

            if (humanIdx.isEmpty() && desIdx.isEmpty()) {
                return k;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || map[nx][ny] == 1)
                    continue;

                pq.add(new int[] {nx, ny, idx, count + 1, state});
                visited[nx][ny] = true;
            }

        }

        return -1;
    }
}

class Index {
    int i;
    int x;
    int y;

    Index(int i, int x, int y) {
        this.i = i;
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, x, y);
    }

    @Override
    public boolean equals(Object o) {
        Index ii = (Index) o;
        return i == ii.i && x == ii.x && y == ii.y;
    }
}