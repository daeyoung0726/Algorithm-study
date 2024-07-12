package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _7562 {

    private static int[][] visited;
    private static int l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer str;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            l = Integer.parseInt(br.readLine());
            visited = new int[l][l];

            str = new StringTokenizer(br.readLine(), " ");
            int startX = Integer.parseInt(str.nextToken());
            int startY = Integer.parseInt(str.nextToken());

            str = new StringTokenizer(br.readLine(), " ");
            int finishX = Integer.parseInt(str.nextToken());
            int finishY = Integer.parseInt(str.nextToken());

            sb.append(bfs(startX, startY, finishX, finishY)).append('\n');
        }

        System.out.println(sb);
    }

    private static int bfs(int startX, int startY, int finishX, int finishY) {
        Queue<Point4> queue = new LinkedList<>();

        queue.add(new Point4(startX, startY));
        visited[startX][startY] = 1;

        int[] dx = { 2, 2, 1, 1, -1, -1, -2, -2 };
        int[] dy = { 1, -1, 2, -2, 2, -2, 1, -1 };

        while (!queue.isEmpty()) {
            Point4 now = queue.poll();

            if (now.getX() == finishX && now.getY() == finishY)
                return visited[now.getX()][now.getY()] - 1;

            for (int i = 0; i < 8; i++) {
                int nx = now.getX() + dx[i];
                int ny = now.getY() + dy[i];

                if (nx >= 0 && ny >= 0 && nx < l && ny < l && visited[nx][ny] == 0) {
                    queue.add(new Point4(nx, ny));
                    visited[nx][ny] = visited[now.getX()][now.getY()] + 1;
                }
            }
        }
        return -1;
    }
}

class Point4 {

    private int x;
    private int y;

    Point4(int x, int y) {
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