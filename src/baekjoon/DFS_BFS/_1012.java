package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1012 {

    private static int[][] visited;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(str.nextToken());   // 가로
            int N = Integer.parseInt(str.nextToken());   // 세로
            int K = Integer.parseInt(str.nextToken());

            int max = Math.max(M, N);

            visited = new int[N][M];

            for (int j = 0; j < K; j++) {
                str = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(str.nextToken());
                int v = Integer.parseInt(str.nextToken());
                visited[v][u] = 1;
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (visited[j][k] == 1) {
                        bfs(j, k, M, N);
                        count++;
                    }
                }
            }

            sb.append(count).append('\n');
            count = 0;
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y, int M, int N) {
        Queue<Point1> queue = new LinkedList<>();

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        queue.add(new Point1(x, y));
        visited[x][y] = 0;

        while (!queue.isEmpty()) {
            Point1 now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.getX() + dx[i];
                int ny = now.getY() + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] == 1) {
                    visited[nx][ny] = 0;
                    queue.add(new Point1(nx, ny));
                }
            }

        }
    }
}

class Point1 {
    private int x;
    private int y;

    Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }
}
