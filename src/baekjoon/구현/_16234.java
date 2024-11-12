package baekjoon.구현;

// bfs로 인접한 나라 탐색.
// visited로 표시하고, true인 나라는 다시 접근 못하게 (떨어져 있는 나라에서도 생길 수 있으니)
// list 2개를 만들어 위치 정보, 인구 수를 넣고, list를 이용해 업뎃 (이거는 접근할 떄마다 초기화)
// 한 번 다 돌면 1초 늘리고, visited는 초기화

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16234 {

    private static int[][] map;
    private static boolean[][] visited;
    private static ArrayList<int[]> locations;
    private static ArrayList<Integer> humanNums;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };

    private static int l;
    private static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        l = Integer.parseInt(str.nextToken());
        r = Integer.parseInt(str.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int time = 0;
        boolean isMoved;
        while (true) {
            visited = new boolean[n][n];
            isMoved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        locations = new ArrayList<>();
                        humanNums = new ArrayList<>();
                        bfs(i, j, n);

                        if (moveHuman())
                            isMoved = true;
                    }
                }
            }

            if (!isMoved) break;

            time++;
        }

        System.out.println(time);
    }

    private static void bfs(int i, int j, int n) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {i, j});
        visited[i][j] = true;

        locations.add(new int[] {i, j});
        humanNums.add(map[i][j]);

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    int sub = Math.abs(map[x][y] - map[nx][ny]);
                    if (sub >= l && sub <= r) {
                        locations.add(new int[] {nx, ny});
                        humanNums.add(map[nx][ny]);
                        queue.add(new int[] {nx, ny});

                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    private static boolean moveHuman() {

        if (humanNums.size() == 1)
            return false;

        int sum = 0;
        for (Integer humanNum : humanNums) {
            sum += humanNum;
        }

        int avg = sum / humanNums.size();

        for (int[] location : locations) {
            int x = location[0];
            int y = location[1];

            map[x][y] = avg;
        }

        return true;
    }
}