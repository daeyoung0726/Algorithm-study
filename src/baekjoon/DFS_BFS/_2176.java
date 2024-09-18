package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2176 {        // 틀림. 문제 해석 아직 불가. 이딴 문제 왜 내냐

    private static ArrayList<ArrayList<int[]>> graphs = new ArrayList<>();
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        distance = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            int c = Integer.parseInt(str.nextToken());

            graphs.get(a).add(new int[]{b, c});
            graphs.get(b).add(new int[]{a, c});
        }
        dijkstra(n);
        System.out.println(bfs(n));
    }

    private static void dijkstra(int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int i = 1; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        pq.add(new int[]{2, 0});
        distance[2] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int dis = now[1];

            if (distance[node] < dis)
                continue;

            for (int[] x : graphs.get(node)) {
                if (distance[x[0]] > distance[node] + x[1]) {
                    distance[x[0]] = distance[node] + x[1];
                    pq.add(new int[]{x[0], distance[x[0]]});
                }
            }
        }
    }

    private static int bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();

        int[] dp = new int[n + 1];
        queue.add(1);
        dp[1] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int[] x : graphs.get(now)) {
                if (distance[now] > distance[x[0]]) {
                    if (dp[x[0]] == 0)
                        queue.add(x[0]);
                    dp[x[0]] += dp[now];
                }
            }
        }

        return dp[2];
    }
}
