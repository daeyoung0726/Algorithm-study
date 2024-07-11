package baekjoon.DFS_BFS;

import java.util.*;

public class _1697 {

    private static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        visited = new int[100001];

        System.out.println(bfs(N, K));
    }

    private static int bfs(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == K) {
                return visited[now] - 1;
            }

            int[] next = { now-1, now+1, now*2 };

            for (int i = 0; i < 3; i++) {
                if (next[i] >= 0 && next[i] <= 100000 && visited[next[i]] == 0) {
                    queue.add(next[i]);
                    visited[next[i]] = visited[now] + 1;
                }
            }

        }
        return -1;
    }
}