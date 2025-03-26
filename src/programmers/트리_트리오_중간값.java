package programmers;

// 하나의 점 잡고 다익스트라 진행
// 그 점을 기준으로 2개 점 더 구해서 중간값 구하기

import java.util.*;

public class 트리_트리오_중간값 {
    private ArrayList<ArrayList<Integer>> graphs;

    public int solution(int n, int[][] edges) {
        graphs = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graphs.get(edge[0]).add(edge[1]);
            graphs.get(edge[1]).add(edge[0]);
        }

        // node1, node2는 서로 가장 멀리 있는 값
        int node1 = getNode(1, n);  // 1에서 가장 멀리 있는 값
        int node2 = getNode(node1, n); // node1에서 가장 멀리 있는 값

        int[] distanceA = getDistance(node1, n);
        int[] distanceB = getDistance(node2, n);

        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (i != node1 && i != node2) {
                int dis1 = distanceA[i];
                int dis2 = distanceB[i];

                answer = Math.max(answer, Math.max(dis1, dis2));
            }
        }


        return answer;
    }

    private int getNode(int num, int n) {
        int[] distance = getDistance(num, n);

        int idx = 0;
        int max = 0;

        for (int i = 1; i < n + 1; i++) {
            if (max < distance[i]) {
                idx = i;
                max = distance[i];
            }
        }

        return idx;
    }

    private int[] getDistance(int start, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        int[] distance = new int[n + 1];

        for (int i = 0; i < n + 1; i++)
            distance[i] = Integer.MAX_VALUE;

        distance[start] = 0;
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (now[1] > distance[now[0]])
                continue;

            for (int next : graphs.get(now[0])) {
                if (distance[next] > distance[now[0]] + 1) {
                    distance[next] = distance[now[0]] + 1;
                    pq.add(new int[] {next, distance[next]});
                }
            }
        }

        return distance;
    }
}