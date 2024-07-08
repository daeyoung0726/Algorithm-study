package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _24480 {

    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private static boolean[] visited;
    private static int[] result;
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());
        int R = Integer.parseInt(str.nextToken());

        visited = new boolean[N+1];
        result = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());

            graphs.get(u).add(v);
            graphs.get(v).add(u);
        }

        for (int i = 1; i < N+1; i++) {
            Collections.sort(graphs.get(i), Collections.reverseOrder());
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < N+1; i++) {
            sb.append(result[i]).append('\n');
        }

        System.out.println(sb);

    }

    private static void dfs(int v) {
        result[v] = count++;
        visited[v] = true;

        for (int x: graphs.get(v)) {
            if (!visited[x])
                dfs(x);
        }
    }
}