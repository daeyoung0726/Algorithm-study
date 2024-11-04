package baekjoon.DFS_BFS;

// bfs이용. 움직일 수 있는 칸만큼 반복해야함. 다 움직였으면 이제 queue에 넣을 수 있는 것 넣기.
// queue넣을 땐, count를 통해 몇 번째 시도인지 확인.
// 우선순위 큐를 통해서 시도 횟수를 오름차순, 만약에 같다면 플레이어 번호를 오름차순

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16920 {

    private static char[][] map;
    private static int[] playerStep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());
        int p = Integer.parseInt(str.nextToken());

        playerStep = new int[p + 1];

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= p; i++) {
            playerStep[i] = Integer.parseInt(str.nextToken());
        }


        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        bfs(n, m);
        countPlayer(n, m, p);
    }

    private static void bfs(int n, int m) {

        Comparator<Player> cmp = (a, b) -> {
            if (a.count == b.count) {
                if (a.num == b.num)
                    return a.step - b.step; // step 해줘야함. 안해주면 더 움직인 것이 먼저 나와 더 멀리 못갈 수 있음 (해당 칸을 차지하면)
                return a.num - b.num;
                }
            return a.count - b.count;
        };

        PriorityQueue<Player> pq = new PriorityQueue<>(cmp);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '.' && map[i][j] != '#') {
                    pq.add(new Player(i, j, 0, map[i][j] - '0', 0));
                }
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!pq.isEmpty()) {
            Player now = pq.poll();

            int x = now.x;
            int y = now.y;
            int count = now.count;
            int num = now.num;
            int step = now.step;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] != '.') {
                    continue;
                }

                map[nx][ny] = (char) (num + '0');
                if (step < playerStep[num] - 1)
                    pq.add(new Player(nx, ny, count, num, step + 1));
                else
                    pq.add(new Player(nx, ny, count + 1, num, 0));
            }
        }
    }

    private static void countPlayer(int n, int m, int p) {

        int[] counts = new int[p + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '#' && map[i][j] != '.') {
                    counts[map[i][j] - '0']++;
                }
            }
        }

        for (int i = 1; i <= p; i++) {
            System.out.print(counts[i] + " ");
        }
    }
}

class Player {
    int x;
    int y;
    int count;
    int num;
    int step;

    Player(int x, int y, int count, int num, int step) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.num = num;
        this.step = step;
    }
}