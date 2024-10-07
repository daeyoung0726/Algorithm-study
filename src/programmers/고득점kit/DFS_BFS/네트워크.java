package programmers.고득점kit.DFS_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class 네트워크 {

    private ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private boolean[] visited;

    public int solution(int n, int[][] computers) {

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    graphs.get(i).add(j);
                    graphs.get(j).add(i);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i);
                answer++;
            }
        }

        return answer;
    }

    private void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        visited[x] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int val: graphs.get(now)) {
                if (!visited[val]) {
                    visited[val] = true;
                    queue.add(val);
                }
            }
        }
    }
}