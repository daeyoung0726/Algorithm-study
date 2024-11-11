package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _15683 {

    private static int[][] map;
    private static int[][] copyMap;
    private static int min = Integer.MAX_VALUE;
    private static ArrayList<CCTV> cctvs;
    private static int[] dir;

    private static int n, m;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };

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
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        dir = new int[cctvs.size()];

        allocateDirection(0, cctvs.size());
        System.out.println(min);
    }

    private static void allocateDirection(int depth, int size) {
        if (depth == size) {
            copyMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < size; i++) {
                putVal(cctvs.get(i), dir[i]);
            }

            calc();
        } else {
            for (int i = 0; i < 4; i++) {
                dir[depth] = i;
                allocateDirection(depth + 1, size);
            }
        }
    }

    private static void putVal(CCTV cctv, int dir) {
        int cctvNum = cctv.num;

        switch (cctvNum) {
            case 1: {
                if (dir == 0) dfs(cctv, 0);
                else if (dir == 1) dfs(cctv, 1);
                else if (dir == 2) dfs(cctv, 2);
                else dfs(cctv, 3);
                break;
            }
            case 2: {
                if (dir == 0 || dir == 2) {    // 상하
                    dfs(cctv, 0);
                    dfs(cctv, 2);
                } else {            // 좌우
                    dfs(cctv, 1);
                    dfs(cctv, 3);
                }
                break;
            }
            case 3: {
                if(dir == 0) {
                    dfs(cctv, 0); // 상우
                    dfs(cctv, 1);
                } else if(dir == 1) {
                    dfs(cctv, 1); // 우하
                    dfs(cctv, 2);
                } else if(dir == 2) {
                    dfs(cctv, 2); // 하좌
                    dfs(cctv, 3);
                } else {
                    dfs(cctv, 0); // 좌상
                    dfs(cctv, 3);
                }
                break;
            }
            case 4: {
                if(dir == 0) {
                    dfs(cctv, 0); // 좌상우
                    dfs(cctv, 1);
                    dfs(cctv, 3);
                } else if(dir == 1) {
                    dfs(cctv, 0); // 상우하
                    dfs(cctv, 1);
                    dfs(cctv, 2);
                } else if(dir == 2) {
                    dfs(cctv, 1); // 좌하우
                    dfs(cctv, 2);
                    dfs(cctv, 3);
                } else {
                    dfs(cctv, 0); // 상좌하
                    dfs(cctv, 2);
                    dfs(cctv, 3);
                }
                break;
            }
            case 5: {
                dfs(cctv, 0);
                dfs(cctv, 1);
                dfs(cctv, 2);
                dfs(cctv, 3);
                break;
            }

        }
    }

    private static void dfs(CCTV cctv, int dir) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {cctv.x, cctv.y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nx = now[0] + dx[dir];
            int ny = now[1] + dy[dir];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] != 6) {
                if (map[nx][ny] == 0) {
                    copyMap[nx][ny] = -1;
                }
                queue.add(new int[] {nx, ny});
            }
        }
    }

    private static void calc() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0)
                    count++;
            }
        }
        min = Math.min(min, count);
    }
}

class CCTV {
    int num;
    int x;
    int y;

    CCTV(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}