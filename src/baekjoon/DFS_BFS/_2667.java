package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2667 {

    private static int[][] viliage;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        viliage = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < s.length(); j++)
                viliage[i][j] = s.charAt(j) - '0';
        }

        StringBuilder sb = new StringBuilder();
        int result = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (viliage[i][j] == 1) {
                    result++;
                    list.add(bfs(i, j));
                }
            }
        }
        Collections.sort(list);

        for (int x: list) {
            sb.append(x).append('\n');
        }
        System.out.println(result);
        System.out.println(sb);
    }

    private static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();

        int count = 0;
        queue.add(new Point(x, y));
        viliage[x][y] = 0;

        while (!queue.isEmpty()) {

            Point now = queue.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = now.getX() + dx[i];
                int ny = now.getY() + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && viliage[nx][ny] == 1) {
                    viliage[nx][ny] = 0;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        return count;
    }
}

class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
