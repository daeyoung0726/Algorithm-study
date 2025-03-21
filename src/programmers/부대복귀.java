package programmers;

// 목적지를 기준으로 한 다익스트라 진행.
// 만약 다익스트라 진행 후, MAX값이라면 해당 위치는 -1 출력

import java.util.*;

public class 부대복귀 {
    private ArrayList<ArrayList<Integer>> graphs;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graphs = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int[] now : roads) {
            graphs.get(now[0]).add(now[1]);
            graphs.get(now[1]).add(now[0]);
        }

        int[] distance = dijkstra(n, destination);

        int[] answer = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            int val = distance[sources[i]] ;
            answer[i] = (distance[sources[i]] != Integer.MAX_VALUE) ? distance[sources[i]] : -1;
        }

        return answer;
    }

    private int[] dijkstra(int n, int destination) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        int[] distance = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        pq.add(new int[] {destination, 0});
        distance[destination] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int idx = now[0];
            int dis = now[1];

            if (distance[idx] < dis)
                continue;

            for (int next : graphs.get(idx)) {
                if (distance[next] > distance[idx] + 1) {
                    distance[next] = distance[idx] + 1;
                    pq.add(new int[] {next, distance[next]});
                }
            }
        }

        return distance;
    }
}