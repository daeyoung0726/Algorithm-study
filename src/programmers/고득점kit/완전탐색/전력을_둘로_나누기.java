package programmers.고득점kit.완전탐색;

import java.util.*;

class 전력을_둘로_나누기 {

    private ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private int min = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            graphs.get(a).add(b);
            graphs.get(b).add(a);
        }

        run(n);
        return min;
    }

    private void run(int n) {

        for (int i = 1; i <= n; i++) {
            int len = graphs.get(i).size();

            for (int j = 0; j < len; j++) {
                int to = graphs.get(i).remove(0);
                if (i < to) {
                    int x = bfs(i, n);
                    min = Math.min(min, Math.abs(x - (n - x)));
                }
                graphs.get(i).add(to);
            }
        }
    }

    private int bfs(int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(start);
        visited[start] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            count++;

            for (int x: graphs.get(now)) {
                if (!visited[x]) {
                    queue.add(x);
                    visited[x] = true;
                }
            }
        }

        return count;
    }
}