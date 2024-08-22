package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _18352 {

    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());
        int x = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n+1; i++) {
            graphs.add(new ArrayList<>());
        }
        distance = new int[n+1];

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(str.nextToken());
            int from = Integer.parseInt(str.nextToken());

            graphs.get(to).add(from);
        }
        dijkstra(x);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == k) {

                sb.append(i).append('\n');
            }
        }
        if (sb.isEmpty()) {
            System.out.println(-1);
        } else
            System.out.println(sb);

    }

    private static void dijkstra(int start) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        for (int i = 1; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        pq.add(start);

        while (!pq.isEmpty()) {
            int now = pq.poll();

            for (int next: graphs.get(now)) {
                if (distance[next] > distance[now] + 1) {
                    distance[next] = distance[now] + 1;
                    pq.add(next);
                }
            }
        }
    }
}