package baekjoon.구현;

// 집과 치킨에 대해서 구분을 함. (list 사용)
// 브루트포스를 통해서 치킨집에 대한 경우의 수 다 구함.
// 치킨집 개수 다 채워지면 계산

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _15686 {

    private static boolean[] visited;
    private static ArrayList<int[]> homes;
    private static ArrayList<int[]> chickens;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        homes = new ArrayList<>();
        chickens = new ArrayList<>();

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        findHomeAndChicken(map, n);
        visited = new boolean[chickens.size()];
        dfs(m, 0, 0);

        System.out.println(min);
    }

    private static void findHomeAndChicken(int[][] map, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    homes.add(new int[] {i, j});
                }
                if (map[i][j] == 2) {
                    chickens.add(new int[] {i, j});
                }
            }
        }
    }

    private static void dfs(int m, int x, int depth) {
        if (m == depth) {
            int sum;
            int result = 0;
            for (int i = 0; i < homes.size(); i++) {
                int[] nowHome = homes.get(i);
                int homeX = nowHome[0];
                int homeY = nowHome[1];
                sum = Integer.MAX_VALUE;

                for (int j = 0; j < chickens.size(); j++) {
                    if (visited[j]) {
                        int[] nowChicken = chickens.get(j);
                        int chickenX = nowChicken[0];
                        int chickenY = nowChicken[1];

                        sum = Math.min(sum, calc(homeX, homeY, chickenX, chickenY));
                    }
                }
                result += sum;
            }
            min = Math.min(min, result);
            return;
        }

        for (int i = x; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(m, i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static int calc(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}