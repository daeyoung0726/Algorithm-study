package baekjoon.구현;

// 재귀를 통해 팀 나누가.
// 전체 수 / 2가 되면 visited를 통해서 true와 false로 나누기.
// 나눌 때는 index 값으로 구분하고, index로 값 구하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _14889 {
    private static int[][] map;
    private static boolean[] visited;
    private static ArrayList<Integer> start;
    private static ArrayList<Integer> link;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n];

        StringTokenizer str;

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        dfs(n, 0, 0);

        System.out.println(min);
    }

    private static void dfs(int n, int x, int depth) {
        if (depth == n / 2) {
            start = new ArrayList<>();
            link = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    start.add(i);
                }
                else {
                    link.add(i);
                }
            }

            int startSum = 0;
            int linkSum = 0;

            int startMember1, startMember2, linkMember1, linkMember2;
            for (int i = 0; i < (n / 2) - 1; i++) {
                startMember1 = start.get(i);
                linkMember1 = link.get(i);
                for (int j = i + 1; j < n / 2; j++) {
                    startMember2 = start.get(j);
                    linkMember2 = link.get(j);

                    startSum += map[startMember1][startMember2];
                    startSum += map[startMember2][startMember1];

                    linkSum += map[linkMember1][linkMember2];
                    linkSum += map[linkMember2][linkMember1];
                }
            }

            min = Math.min(min, Math.abs(startSum - linkSum));
            return;
        }

        for (int i = x; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(n, i + 1, depth + 1);
                visited[i] = false;
            }
        }

    }
}