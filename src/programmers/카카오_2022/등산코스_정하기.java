package programmers.카카오_2022;

import java.util.*;

public class 등산코스_정하기 {

    private ArrayList<ArrayList<int[]>> graphs = new ArrayList<>();
    private int minIntensity = Integer.MAX_VALUE;
    private int minIdx = Integer.MAX_VALUE;
    private Set<Integer> gateSet = new HashSet<>();
    private Set<Integer> summitSet = new HashSet<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < paths.length; i++) {
            int x = paths[i][0];
            int y = paths[i][1];
            int cost = paths[i][2];

            graphs.get(x).add(new int[] {y, cost});
            graphs.get(y).add(new int[] {x, cost});

        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        dijkstra(n, gates);

        return new int[] {minIdx, minIntensity};
    }

    private void dijkstra(int n, int[] gates) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int gate: gates) {
            distance[gate] = 0;
            pq.add(new int[] {gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int x = now[0];
            int cost = now[1];

            if (summitSet.contains(x)) {
                if (minIntensity >= cost) {
                    if (minIntensity == cost) {
                        minIdx = Math.min(minIdx, x);
                    } else {
                        minIdx = x;
                    }
                    minIntensity = cost;
                }

                continue;
            }

            if (cost > distance[x])
                continue;

            for (int[] next: graphs.get(x)) {

                int cc = Math.max(cost, next[1]);
                if (!gateSet.contains(next[0]) && distance[next[0]] > cc) {
                    distance[next[0]] = cc;
                    pq.add(new int[] {next[0], distance[next[0]]});
                }
            }
        }

    }
}