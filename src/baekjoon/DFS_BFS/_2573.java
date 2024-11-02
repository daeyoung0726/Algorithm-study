package baekjoon.DFS_BFS;

// bfs로 2개로 나눠졌는지 확인 (이거는 1년 지날 때마다 모든 index다 돌아야 할듯. 방문한 곳은 visited로 표시)
// visited는 1년마다 초기화 (반복문 시작할때마다 초기화)
// 빙산이 0이 아닌 곳은 상, 하, 좌, 우 0이 있는지 카운트 해서 미리 list에 저장 후, 모두 다 카운트하면 -시킨다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2573 {

    private static int[][] map;
    private static boolean[][] visited;
    private static ArrayList<int[]> nodes;

    private static final int[] dx = { -1, 0, 1, 0 };
    private static final int[] dy = { 0, -1, 0 ,1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int time = 0;
        int count;

        boolean isTrue = false;
        while (true) {
            time++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0) {
                        countMap(i, j);
                    }
                }
            }

            for (int[] node: nodes) {
                map[node[0]][node[1]] -= node[2];
                if (map[node[0]][node[1]] < 0)
                    map[node[0]][node[1]] = 0;
            }
            nodes.clear();

            visited = new boolean[n][m];

            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j, n, m);
                        count++;
                    }
                }
            }

            if (count >= 2) {
                isTrue = true;
                break;
            }

            if (count == 0) {
                isTrue = false;
                break;
            }
        }

        if (isTrue) {
            System.out.println(time);
        } else {
            System.out.println(0);
        }
    }

    private static void countMap(int x, int y) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            if (map[x + dx[i]][y + dy[i]] == 0) {
                count++;
            }
        }

        nodes.add(new int[] {x, y, count});
    }

    private static void bfs(int i, int j, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    if (map[nx][ny] != 0) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}