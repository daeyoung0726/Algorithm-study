package baekjoon.문제집.코테대비;

// 모든 경우의 수를 탐색. 브루트포스
// 경우의 숫에 대한 값은 배열에 저장
// 사이에 0이 존재하면 실패
// 경로 값 구할 떄, 마지막 수와 첫번째 수 이을 수 없으면 그것도 실패.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _10971 {

    private static int[][] map;
    private static boolean[] visited;
    private static int[] arr;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer str;

        map = new int[n][n];
        visited = new boolean[n];
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        dfs(n, 0);
        System.out.println(min);
    }

    private static void dfs(int n, int depth) {
        if (depth == n) {

            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                int num = map[arr[i]][arr[i + 1]];
                if (num == 0) {
                    return;
                }
                sum += num;
            }
            int x = map[arr[n - 1]][arr[0]];
            if (x == 0) {
                return;
            }
            sum += x;

            min = Math.min(min, sum);

            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                dfs(n, depth + 1);
                visited[i] = false;
            }
        }
    }
}