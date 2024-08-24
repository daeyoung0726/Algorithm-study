package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14503 {

    private static int[][] location;
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int r;
    private static int c;

    private static int[] dr = {-1, 0, 1, 0}; // 북, 동, 남, 서
    private static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        location = new int[n][m];
        visited = new boolean[n][m];

        str = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(str.nextToken());
        c = Integer.parseInt(str.nextToken());
        int v = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                location[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int count = 1;
        visited[r][c] = true;

        while (true) {
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                v = (v + 3) % 4; // 왼쪽 방향으로 회전
                int newR = r + dr[v];
                int newC = c + dc[v];

                if (isInBounds(newR, newC) && !visited[newR][newC] && location[newR][newC] == 0) {
                    r = newR;
                    c = newC;
                    visited[r][c] = true;
                    count++;
                    moved = true;
                    break;
                }
            }

            if (!moved) { // 후진 조건
                int back = (v + 2) % 4;
                int newR = r + dr[back];
                int newC = c + dc[back];
                if (isInBounds(newR, newC) && location[newR][newC] == 0) {
                    r = newR;
                    c = newC;
                } else {
                    break;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isInBounds(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}

/*
public class _14503 {

    private static int[][] location;
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        location = new int[n][m];
        visited = new boolean[n][m];

        str = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(str.nextToken());
        c = Integer.parseInt(str.nextToken());
        int v = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < m; j++) {
                location[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int count = 1;
        visited[r][c] = true;
        while (true) {
            if (dirtyCheck()) {
                v = (v == 0) ? 3 : (v-1);
                if (moveCheck(v)) {
                    visited[r][c] = true;
                    count++;
                }
            } else {
                int inverseV = (2 + v) % 4;
                if (!wallCheck(inverseV)) {
                    break;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean wallCheck(int v) {
        switch(v) {
            case 0: {
                if (r == 0)
                    return false;
                if (location[r-1][c] == 0) {
                    r = r-1;
                    return true;
                }
                break;
            }
            case 1: {
                if (c == m-1)
                    return false;
                if (location[r][c+1] == 0) {
                    c = c+1;
                    return true;
                }
                break;
            }
            case 2: {
                if (r == n-1)
                    return false;
                if (location[r+1][c] == 0) {
                    r = r+1;
                    return true;
                }
                break;
            }
            case 3: {
                if (c == 0)
                    return false;
                if (location[r][c-1] == 0) {
                    c = c-1;
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private static boolean moveCheck(int v) {

        switch(v) {
            case 0: {
                if (r == 0 || visited[r-1][c])
                    return false;
                if (location[r-1][c] == 0) {
                    r = r-1;
                    return true;
                }
                break;
            }
            case 1: {
                if (c == m-1 || visited[r][c+1])
                    return false;
                if (location[r][c+1] == 0) {
                    c = c+1;
                    return true;
                }
                break;
            }
            case 2: {
                if (r == n-1 || visited[r+1][c])
                    return false;
                if (location[r+1][c] == 0) {
                    r = r+1;
                    return true;
                }
                break;
            }
            case 3: {
                if (c == 0 || visited[r][c-1])
                    return false;
                if (location[r][c-1] == 0) {
                    c = c-1;
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private static boolean dirtyCheck() {
        if (r == 0) {
            if (c == 0) {
                if ((location[r+1][c] == 1 || visited[r+1][c])
                        && (location[r][c+1] == 1 || visited[r][c+1]))
                    return false;
            } else if (c == m-1) {
                if ((location[r+1][c] == 1 || visited[r+1][c])
                        && (location[r][c-1] == 1 || visited[r][c-1]))
                    return false;
            } else {
                if ((location[r+1][c] == 1 || visited[r+1][c])
                        && (location[r][c-1] == 1 || visited[r][c-1])
                        && (location[r][c+1] == 1 || visited[r][c+1]))
                    return false;
            }
        }

        if (r == n-1) {
            if (c == 0) {
                if ((location[r-1][c] == 1 || visited[r-1][c])
                        && (location[r][c+1] == 1 || visited[r][c+1]))
                    return false;
            } else if (c == m-1) {
                if ((location[r-1][c] == 1 || visited[r-1][c])
                        && (location[r][c-1] == 1 || visited[r][c-1]))
                    return false;
            } else {
                if ((location[r-1][c] == 1 || visited[r-1][c])
                        && (location[r][c-1] == 1 || visited[r][c-1])
                        && (location[r][c+1] == 1 || visited[r][c+1]))
                    return false;
            }
        }

        if ((location[r-1][c] == 1 || visited[r-1][c])
                && (location[r+1][c] == 1 || visited[r+1][c])
                && (location[r][c-1] == 1 || visited[r][c-1])
                && (location[r][c+1] == 1 || visited[r][c+1]))
            return false;

        return true;
    }


}
*/
