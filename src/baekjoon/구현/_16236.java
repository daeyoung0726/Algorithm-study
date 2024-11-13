package baekjoon.구현;

// bfs사용
// 크기와 먹는거 기록 해야함.
// 정렬할 떄, 카운터가 작은걸 정렬하고, 만약에 같다면, x가 작은거.
// 만약에 x까지 같은게 여러개라면, y가 작은걸로 정렬
// 만약, 값을 찾고 구했다? visited 다시 만들고, queue도 다시 초기화

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16236 {

    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        StringTokenizer str;

        int x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(str.nextToken());
                map[i][j] = num;

                if (num == 9) {
                    x = i;
                    y = j;
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(bfs(x, y, n));
    }

    private static int bfs(int x, int y, int n) {

        Comparator<Node> cmp = (a, b) -> {
            if (a.count == b.count) {
                if (a.x == b.x) {
                    return a.y - b.y;
                }
                return a.x - b.x;
            }
            return a.count - b.count;
        };

        PriorityQueue<Node> pq = new PriorityQueue<>(cmp);
        boolean[][] visited = new boolean[n][n];

        pq.add(new Node(x, y, 0));
        visited[x][y] = true;

        int sharkSize = 2;
        int eatFish = 0;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        int move = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int x1 = now.x;
            int y1 = now.y;
            int nowCount = now.count;

            if (map[x1][y1] != 0 && sharkSize > map[x1][y1]) {
                map[x1][y1] = 0;
                eatFish++;
                move += nowCount;
                nowCount = 0;
                if (eatFish == sharkSize) {
                    sharkSize++;
                    eatFish = 0;
                }
                visited = new boolean[n][n];
                visited[x1][y1] = true;
                pq.clear();
            }

            for (int i = 0; i < 4; i++) {

                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    if (map[nx][ny] <= sharkSize) {
                        pq.add(new Node(nx, ny, nowCount + 1));
                    }
                    visited[nx][ny] = true;
                }
            }
        }

        return move;
    }
}

class Node {
    int x;
    int y;
    int count;

    Node(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
