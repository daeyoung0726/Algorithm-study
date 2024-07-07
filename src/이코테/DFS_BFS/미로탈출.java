package 이코테.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탈출 {

    private static int[][] arr;
    private static int N;
    private static int M;
    private static int min = Integer.MAX_VALUE;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        dfs(0, 0, 1);
        System.out.println(min);
        System.out.println(bfs(0, 0));
    }

    private static void dfs(int x, int y, int count) {

        if (x == N-1 && y == M-1) {
            min = Math.min(count, min);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] == 1) {
                arr[nx][ny] = 0;
                dfs(nx, ny, count+1);
                arr[nx][ny] = 1;
            }
        }
    }

    private static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.getX() + dx[i];
                int ny = now.getY() + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 1) {
                    arr[nx][ny] = arr[now.getX()][now.getY()] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        return arr[N-1][M-1];
    }
}

class Point {
    private int x;
    private int y;

    Point(int x, int y) {
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