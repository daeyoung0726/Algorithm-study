package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1916 {

    private static ArrayList<ArrayList<Node1>> graphs = new ArrayList<>();
    private static boolean[] visited;
    private static int[] distance;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        distance = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            graphs.add(new ArrayList<>());
        }

        StringTokenizer str;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(str.nextToken());
            int to = Integer.parseInt(str.nextToken());
            int dis = Integer.parseInt(str.nextToken());

            graphs.get(from).add(new Node1(to, dis));
        }

        str = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(str.nextToken());
        int end = Integer.parseInt(str.nextToken());

        priorityQueueDijkstra(start);

        System.out.println(distance[end]);
    }

    private static void dijkstra(int start) {

        for (int i = 1; i < n+1; i++) {
            distance[i] = Integer.MAX_VALUE / 2;
        }

        distance[start] = 0;

        for (int i = 0; i < n-1; i++) {
            int min = getMinimum();

            if (min == -1)
                continue;

            visited[min] = true;

            for (Node1 node: graphs.get(min)) {
                if (!visited[node.to] && distance[node.to] > distance[min] + node.dis)
                    distance[node.to] = distance[min] + node.dis;
            }
        }
    }

    private static int getMinimum() {
        int minIdx = -1;
        int minVal = Integer.MAX_VALUE;

        for (int i = 1; i < n+1; i++) {
            if (!visited[i] && minVal > distance[i]) {
                minIdx = i;
                minVal = distance[i];
            }
        }

        return minIdx;
    }

    private static void priorityQueueDijkstra(int start) {
        PriorityQueue<Node1> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);

        for (int i = 1; i < n+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;
        pq.add(new Node1(start, 0));

        while (!pq.isEmpty()) {
            Node1 now = pq.poll();

            visited[now.to] = true;

            // 최단경로가 확정된 경우. 그러니깐 처음에 now.to의 경로에 대해서 8이 들어갔는데,
            // 다른 동작으로 인해 5로 바뀌게 되면 8에 대한 동작은 필요가 없음. 그래서 이러한 로직을 설계
            if (distance[now.to] < now.dis) {
                continue;
            }

            for (Node1 node: graphs.get(now.to)) {
                if (!visited[node.to] && distance[node.to] > distance[now.to] + node.dis) {
                    distance[node.to] = distance[now.to] + node.dis;
                    pq.add(new Node1(node.to, distance[node.to]));
                }
            }
        }
    }
}

class Node1 {
    int to;
    int dis;

    Node1(int to, int dis) {
        this.to = to;
        this.dis = dis;
    }
}