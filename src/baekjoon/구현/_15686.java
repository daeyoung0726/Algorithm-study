package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _15686 {

    private static ArrayList<Point1> houses = new ArrayList<>();
    private static ArrayList<Point1> chickens = new ArrayList<>();
    private static int n;
    private static int m;
    private static int min = Integer.MAX_VALUE;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while(str.hasMoreTokens()) {
                map[i][j++] = Integer.parseInt(str.nextToken());
            }
        }

        findHouseAndChicken(map);
        visited = new boolean[chickens.size()];

        dfs(0, 0);

        System.out.println(min);
    }

    private static void findHouseAndChicken(int[][] map) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    houses.add(new Point1(i, j));
                }
                if (map[i][j] == 2) {
                    chickens.add(new Point1(i, j));
                }
            }
        }
    }

    private static void dfs(int start, int cnt) {
        if (cnt == m) {
            int total = 0;

            for (int i = 0; i < houses.size(); i++) {
                int sum = Integer.MAX_VALUE;
                Point1 house = houses.get(i);
                for (int j = 0; j < chickens.size(); j++) {
                    if (visited[j]) {
                        Point1 chicken = chickens.get(j);
                        sum = Math.min(sum,
                                Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y));
                    }
                }
                total += sum;
            }
            min = Math.min(min, total);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }
}

class Point1 {
    int x;
    int y;

    Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
