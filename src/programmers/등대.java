package programmers;

// 연결된 횟수를 계산. 연결된 정점들 list에 저장.
// 리프노드들 부터 확인.

import java.util.*;

public class 등대 {
    private ArrayList<ArrayList<Integer>> graphs;
    private int[] inDegree;

    public int solution(int n, int[][] lighthouse) {
        graphs = new ArrayList<>();
        inDegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        int len = lighthouse.length;

        for (int i = 0; i < len; i++) {
            int x = lighthouse[i][0];
            int y = lighthouse[i][1];

            graphs.get(x).add(y);
            graphs.get(y).add(x);
            inDegree[x]++;
            inDegree[y]++;
        }

        return run(n);
    }

    private int run(int n) {
        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[n + 1];
        boolean[] lightOn = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 1)
                queue.add(i);
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            visited[now] = true;

            for (int next : graphs.get(now)) {
                if (!visited[next]) {
                    if (!lightOn[now] && !lightOn[next]) {
                        lightOn[next] = true;
                        count++;
                    }

                    inDegree[next]--;

                    if (inDegree[next] == 1) {
                        queue.add(next);
                    }
                }
            }

        }

        return count;

    }
}