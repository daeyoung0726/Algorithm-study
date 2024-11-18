package baekjoon.DFS_BFS;

// bfs이용
// map에 체크
// 돌고돌아 처음의 값이 나온다면 성공
// 안나온다면 실패

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _9466 {

    private static int[] arr;
    private static boolean[] visited, finished;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer str;
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            str = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                arr[j] = Integer.parseInt(str.nextToken());
            }

            for (int j = 1; j <= n; j++) {
                if (!visited[j])
                    dfs(j);
            }
            sb.append((n - count) + "\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int start) {
        visited[start] = true;
        int next = arr[start];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            for (int i = next; i != start; i = arr[i])
                count++;
            count++;
        }

        finished[start] = true;
    }
}
