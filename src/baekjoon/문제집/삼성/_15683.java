package baekjoon.문제집.삼성;

// CCTV에 대한 정보를 저장 (위치, CCTV 숫자)
// 브루트포스를 통해서 CCTV에 바라보는 방향 할당
// 번호와 방향에 따라서 bfs를 통해 감시할 수 있는 곳까지 감시
// bfs 끝나면 카운트 호출해서 저장

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _15683 {

    private static int[][] map;
    private static int[][] cloneMap;
    private static ArrayList<CCTV> cctvs;
    private static int[] dirs;
    private static int n;
    private static int m;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        cctvs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(str.nextToken());
                map[i][j] = num;
                if (num != 0 && num != 6) {
                    cctvs.add(new CCTV(i, j, num));
                }
            }
        }

        dirs = new int[cctvs.size()];

        dfs(0);

        System.out.println(min);
    }

    private static void dfs(int depth) {
        if (depth == cctvs.size()) {
            cloneMap = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cloneMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < cctvs.size(); i++) {
                CCTV cctv = cctvs.get(i);
                runCCTV(cctv, dirs[i]);
            }

            count();
            return;
        }

        for (int i = 0; i < 4; i++) {
            dirs[depth] = i;
            dfs(depth + 1);
        }
    }

    private static void runCCTV(CCTV cctv, int d) {
        int num = cctv.num;

        switch (num) {
            case 1: {
                bfs(cctv.x, cctv.y, d);
                break;
            }
            case 2: {
                bfs(cctv.x, cctv.y, d);
                bfs(cctv.x, cctv.y, (d + 2) % 4);
                break;
            }
            case 3: {
                bfs(cctv.x, cctv.y, d);
                bfs(cctv.x, cctv.y, (d + 1) % 4);
                break;
            }
            case 4: {
                bfs(cctv.x, cctv.y, d);
                bfs(cctv.x, cctv.y, (d + 1) % 4);
                bfs(cctv.x, cctv.y, (d - 1) >= 0 ? d - 1 : 3);
                break;
            }
            case 5: {
                bfs(cctv.x, cctv.y, 0);
                bfs(cctv.x, cctv.y, 1);
                bfs(cctv.x, cctv.y, 2);
                bfs(cctv.x, cctv.y, 3);
            }
        }
    }

    private static void bfs(int x, int y, int d) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nx = now[0] + dx[d];
            int ny = now[1] + dy[d];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || cloneMap[nx][ny] == 6) {
                break;
            }
            cloneMap[nx][ny] = -1;
            queue.add(new int[] {nx, ny});
        }
    }

    private static void count() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cloneMap[i][j] == 0)
                    count++;
            }
        }

        min = Math.min(min, count);
    }
}

class CCTV {
    int x;
    int y;
    int num;

    CCTV(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
}