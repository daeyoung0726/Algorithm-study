package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2606 {

    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private static boolean[] visited;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        for (int i = 0; i < n+1; i++) {
            graphs.add(new ArrayList<>());
        }

        StringTokenizer str;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());
            graphs.get(u).add(v);
            graphs.get(v).add(u);
        }

        bfs(1);

        System.out.println(count);
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int y: graphs.get(x)) {
                if (!visited[y]) {
                    count++;
                    queue.add(y);
                    visited[y] = true;
                }
            }
        }
    }
}
