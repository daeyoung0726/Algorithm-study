package 이코테.DFS_BFS;

import java.util.LinkedList;

public class DFS {

    private static LinkedList<Integer>[] graphs;
    private static boolean[] visited;

    public static void main(String[] args) {

        graphs = new LinkedList[9];
        visited = new boolean[9];

        for (int i = 0; i < 9; i++) {
            graphs[i] = new LinkedList<>();
        }

        graphs[1].add(2);
        graphs[1].add(3);
        graphs[1].add(8);

        graphs[2].add(1);
        graphs[2].add(7);

        graphs[3].add(1);
        graphs[3].add(4);
        graphs[3].add(5);

        graphs[4].add(3);
        graphs[4].add(5);

        graphs[5].add(3);
        graphs[5].add(4);

        graphs[6].add(7);

        graphs[7].add(2);
        graphs[7].add(6);
        graphs[7].add(8);

        graphs[8].add(1);
        graphs[8].add(7);

        dfs(1);
    }

    private static void dfs(int v) {

        visited[v] = true;
        System.out.print(v + " ");

        for (int x: graphs[v]) {
            if(!visited[x])
                dfs(x);
        }
    }
}
