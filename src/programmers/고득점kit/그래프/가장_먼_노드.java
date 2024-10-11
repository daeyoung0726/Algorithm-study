package programmers.고득점kit.그래프;

import java.util.*;

class 가장_먼_노드 {

    private ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    public int solution(int n, int[][] edge) {

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];

            graphs.get(x).add(y);
            graphs.get(y).add(x);
        }

        return bfs(1, n);
    }

    private int bfs(int start, int n) {
        Queue<int[]> queue = new LinkedList<>();

        boolean[] visited = new boolean[n + 1];

        visited[start] = true;
        queue.add(new int[] {start, 0});

        int total = 0;
        int maxCount = 0;
        boolean check;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int count = now[1];

            check = false;

            for (int v: graphs.get(x)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(new int[] {v, count + 1});
                    check = true;
                }
            }
            if (!check) {
                if (maxCount == count) {
                    total++;
                } else {
                    total = 1;
                    maxCount = count;
                }
            }
        }

        return total;
    }
}