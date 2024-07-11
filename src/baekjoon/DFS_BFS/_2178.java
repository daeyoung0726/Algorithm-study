package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2178 {

    private static int[][] visited;
    private static int min = Integer.MAX_VALUE;
    private static int N;
    private static int M;
    private static int[] dx = { -1, 1, 0, 0 };
    private static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());

        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for(int j = 0; j < s.length(); j++) {
                visited[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(bfs(0, 0));
    }

    private static void dfs(int x, int y, int count) {

        if (x == N-1 && y == M-1) {
            min = Math.min(min, count);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] == 1) {
                visited[nx][ny] = 0;
                dfs(nx, ny, count+1);
                visited[nx][ny] = 1;
            }
        }
    }

    private static int bfs(int x, int y) {
        Queue<Point2> queue = new LinkedList<>();

        queue.add(new Point2(x, y));

        while (!queue.isEmpty()) {
            Point2 now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.getX() + dx[i];
                int ny = now.getY() + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny] == 1) {
                    visited[nx][ny] = visited[now.getX()][now.getY()] + 1;
                    queue.add(new Point2(nx, ny));
                }
            }
        }

        return visited[N-1][M-1];
    }
}

class Point2 {
    private int x;
    private int y;

    Point2(int x, int y) {
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