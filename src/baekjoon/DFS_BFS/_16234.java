package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16234 {

    private static int[][] map;
    private static int[][] cloneMap;
    private static boolean[][] visited;
    private static ArrayList<Point7> list;
    private static int n;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(str.nextToken());
        int l = Integer.parseInt(str.nextToken());
        int r = Integer.parseInt(str.nextToken());

        map = new int[n][n];
        cloneMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(str.nextToken());
                map[i][j] = value;
                cloneMap[i][j] = value;
            }
        }
        int sum;
        int count = 0;
        while (true) {
            boolean check = false;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        list = new ArrayList<>();
                        sum = bfs(i, j, l, r);
                        int result = 0;
                        if (sum != 0 && list.size() != 1) {
                            check = true;
                            result = sum / (list.size());

                            for (Point7 p : list) {
                                map[p.x][p.y] = result;
                            }
                        }
                    }
                }

            }

            if (check) {
                count++;
            } else
                break;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cloneMap[i][j] = map[i][j];
                }
            }
        }

        System.out.println(count);

    }

    private static int bfs(int i, int j, int l, int r) {
        Queue<Point7> queue = new LinkedList<>();

        queue.add(new Point7(i, j));
        list.add(new Point7(i, j));

        visited[i][j] = true;

        int sum = cloneMap[i][j];

        while (!queue.isEmpty()) {
            Point7 p = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    int sub = Math.abs(cloneMap[p.x][p.y] - cloneMap[nx][ny]);
                    if (sub >= l && sub <= r) {
                        sum += cloneMap[nx][ny];
                        visited[nx][ny] = true;
                        list.add(new Point7(nx, ny));
                        queue.add(new Point7(nx, ny));
                    }
                }
            }
        }

        return sum;
    }


}

class Point7 {
    int x;
    int y;

    Point7(int x, int y) {
        this.x = x;
        this.y = y;
    }
}