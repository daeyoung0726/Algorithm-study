package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _18428 {

    private static char[][] map;
    private static int n;
    private static boolean foundSolution = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = str.nextToken().charAt(0);
            }
        }
        dfs(0, 0);
        if (!foundSolution)
            System.out.println("NO");

    }

    private static void dfs(int x, int depth) {

        if  (foundSolution)
            return;

        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = x; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(i, depth+1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    private static void bfs() {
        Queue<Point6> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'T')
                    queue.add(new Point6(i, j));
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        while (!queue.isEmpty()) {
            Point6 now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x;
                int ny = now.y;

                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                    if (map[nx][ny] == 'O') break;
                    if (map[nx][ny] == 'S') return;
                }


            }
        }

        foundSolution = true;
        System.out.println("YES");

    }
}

class Point6 {
    int x;
    int y;

    Point6(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/*public class _18428 {

    private static char[][] map;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = str.nextToken().charAt(0);
            }
        }
        dfs(0, 0);
        System.out.println("NO");

    }

    private static void dfs(int x, int depth) {
        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = x; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(i, depth+1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    private static void bfs() {
        Queue<Point6> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'T')
                    queue.add(new Point6(i, j));
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, -1, 0, 1 };

        while (!queue.isEmpty()) {
            Point6 now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x;
                int ny = now.y;

                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                    if (map[nx][ny] == 'O') break;
                    if (map[nx][ny] == 'S') return;
                }


            }
        }


        System.out.println("YES");
        System.exit(0);

    }
}

class Point6 {
    int x;
    int y;

    Point6(int x, int y) {
        this.x = x;
        this.y = y;
    }
}*/
