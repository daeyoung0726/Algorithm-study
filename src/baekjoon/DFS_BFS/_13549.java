package baekjoon.최단경로;

import java.util.*;

public class _13549 {

    private static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        visited = new int[100001];

        System.out.println(bfs(n, k));
    }

    private static int bfs(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();

        visited[n] = 1;
        queue.add(n);

        while(!queue.isEmpty()) {
            int now = queue.poll();

            if (now == k) {
                return visited[now] - 1;
            }

            int[] next = { 2*now, now-1, now+1};        // 순간이동 (2*now)는 0초이기에 먼저 처리해줘야함.

            for (int i = 0; i < 3; i++) {
                if (next[i] >= 0 && next[i] < 100001 && visited[next[i]] == 0) {
                    if (i == 0) {
                        visited[next[i]] = visited[now];
                    } else {
                        visited[next[i]] = visited[now] + 1;
                    }
                    queue.add(next[i]);
                }
            }
        }

        return -1;
    }
}