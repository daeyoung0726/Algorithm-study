package 이코테.DFS_BFS;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

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

        bfs(1);
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            System.out.print(x + " ");
            for (int y: graphs[x]) {
                if (!visited[y]) {
                    visited[y] = true;
                    queue.add(y);
                }
            }
        }
    }
}
