package programmers.카카오_2021;

// 하나의 지점 정한 후, 그 지점에서 다익스트라를 하여 a,b를 찾는방식

import java.util.*;

public class 합승_택시_요금 {

    private ArrayList<ArrayList<int[]>> graphs = new ArrayList<>();

    public int solution(int n, int s, int a, int b, int[][] fares) {

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < fares.length; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int dis = fares[i][2];

            graphs.get(x).add(new int[] {y, dis});
            graphs.get(y).add(new int[] {x, dis});
        }

        int[] distanceS = dijkstra(s, n);
        int[] distanceA = dijkstra(a, n);
        int[] distanceB = dijkstra(b, n);

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            min = Math.min(min, distanceS[i] + distanceA[i] + distanceB[i]);
        }

        return min;
    }

    private int[] dijkstra(int start, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);

        int[] distance = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int nn = now[0];
            int dis = now[1];
            if (distance[nn] < dis)
                continue;
            for (int[] next: graphs.get(nn)) {
                if (distance[next[0]] > distance[nn] + next[1]) {
                    distance[next[0]] = distance[nn] + next[1];
                    pq.add(new int[] {next[0], distance[next[0]]});
                }
            }
        }

        return distance;
    }
}