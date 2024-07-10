package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1260 {

    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private static boolean[] visitedDFS;
    private static boolean[] visitedBFS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());
        int r = Integer.parseInt(str.nextToken());

        visitedDFS = new boolean[n+1];
        visitedBFS = new boolean[n+1];
        for (int i = 0; i < n+1; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());
            graphs.get(u).add(v);
            graphs.get(v).add(u);
        }

        for (int i = 1; i < n+1; i++) {
            Collections.sort(graphs.get(i));
        }

        dfs(r);
        System.out.println();
        bfs(r);
    }

    private static void dfs(int v) {
        visitedDFS[v] = true;
        System.out.print(v + " ");

        for (int x: graphs.get(v)) {
            if (!visitedDFS[x])
                dfs(x);
        }
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visitedBFS[v] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            System.out.print(x + " ");
            for (int y: graphs.get(x)) {
                if (!visitedBFS[y]) {
                    visitedBFS[y] = true;
                    queue.add(y);
                }
            }
        }
    }
}
