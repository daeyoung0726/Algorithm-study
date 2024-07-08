package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _24479 {

    private static int[] result;
    private static boolean[] visited;
    private static int count = 1;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());
        int R = Integer.parseInt(str.nextToken());

        result = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        String inputLine;
        while ((inputLine = br.readLine()) != null && !inputLine.trim().isEmpty()) {
            str = new StringTokenizer(inputLine, " ");
            while (str.hasMoreTokens()) {
                int u = Integer.parseInt(str.nextToken());
                int v = Integer.parseInt(str.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }

        for (int i = 1; i < N+1; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < result.length; i++) {
            sb.append(result[i]).append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int v) {

        result[v] = count++;
        visited[v] = true;

        for (int x: graph.get(v)) {
            if (!visited[x])
                dfs(x);
        }
    }
}
