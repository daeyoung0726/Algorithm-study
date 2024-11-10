package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// dx, dy를 설정.
// 주변 네 곳 지저분한지 확인. 만약 지저분하다면, 반시계로 회전하고, 앞으로 전진 가능한지 확인(더러운지)
// 없다면 후진. 후진 못하면 종료
// 1이 벽, 0이 청소안된 것. 로봇 청소기는 무조건 0에 위치하기에 청소 디폴트값은 1 (6:30)

public class _14503 {

    private static int[][] map;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int x;
    private static int y;
    private static int dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n][m];

        str = new StringTokenizer(br.readLine(), " ");
        x = Integer.parseInt(str.nextToken());
        y = Integer.parseInt(str.nextToken());
        dir = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int count = 1;
        map[x][y] = 2;  // 처음 위치 2로 바꿔주기.       이것보단 아래 원래 식처럼 visited를 해주는게 좋을끼? 그런데 그 문제도 결국엔 처음 것에대한  처리 있어야함
        while (true) {

            if (isDirty(n, m)) {    // 4칸 확인 후 있다면,
                count++;
            } else {
                if (!move(n, m)) {
                    break;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isDirty(int n, int m) {

        int nx, ny;
        int d = dir;
        for (int i = 0; i < 4; i++) {
            d = (d - 1 >= 0) ? (d - 1) : 3;
            nx = x + dx[d];
            ny = y + dy[d];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 0) {
                x += dx[d];
                y += dy[d];
                map[nx][ny] = 2;
                dir = d;
                return true;
            }
        }

        return false;
    }

    private static boolean move(int n, int m) {
        int d = (dir + 2) % 4;
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 1) {
            return false;
        }

        x = nx;
        y = ny;
        return true;
    }
}

/*public class _14503 {

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
}*/

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
