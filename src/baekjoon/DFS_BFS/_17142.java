package baekjoon.DFS_BFS;

// 완전탐색으로 바이러스 모든 경우의 수를 구함
// 구한 바이러스 위치 bfs 실행 (map은 copy해서 사용) - 시간은 queue 빼낼 때 마다 time을 Math.max
// 바이러스 퍼진 map을 사용해 전체 바이러스 퍼졌는지 확인 (만약 안퍼졌다면 시간 업데이트 x)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _17142 {
    private static int[][] map;
    private static ArrayList<int[]> virus;
    private static boolean[] isSelected;
    private static int n;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());

        n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n][n];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(str.nextToken());
                map[i][j] = num;

                if (num == 2)
                    virus.add(new int[] {i, j});
            }
        }

        isSelected = new boolean[virus.size()];

        dfs(m, 0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void dfs(int m, int depth, int start) {
        if (m == depth) {
            bfs();
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            isSelected[i] = true;
            dfs(m, depth + 1, i + 1);
            isSelected[i] = false;
        }
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        int[][] copyMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < isSelected.length; i++) {
            if (isSelected[i]) {
                int[] nn = virus.get(i);
                queue.add(new int[] {nn[0], nn[1], 0});
            }
        }

        int time = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int count = now[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || copyMap[nx][ny] == 1 || copyMap[nx][ny] == -1)
                    continue;

                queue.add(new int[] {nx, ny, count + 1});

                if (copyMap[nx][ny] == 0)
                    time = Math.max(time, count + 1);

                copyMap[nx][ny] = -1;

            }
        }

        mapCount(copyMap, time);
    }

    private static void mapCount(int[][] copyMap, int time) {
        boolean check = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(copyMap[i][j] == 0) {
                    check = false;
                    break;
                }
            }

            if (!check)
                break;
        }

        if (check)
            min = Math.min(min, time);
    }
}