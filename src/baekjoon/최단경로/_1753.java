package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class _1753 {

    private static ArrayList<ArrayList<Point>> graph = new ArrayList<>();
    private static int[] distance;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int v = Integer.parseInt(str.nextToken());
        int e = Integer.parseInt(str.nextToken());

        distance = new int[v+1];
        visited = new boolean[v+1];

        for (int i = 0; i < v+1; i++) {
            graph.add(new ArrayList<>());
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < e; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            int w = Integer.parseInt(str.nextToken());

            graph.get(x).add(new Point(y, w));
        }

        dijkstraPriorityQueue(start, v);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < v+1; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                sb.append("INF").append('\n');
            else
                sb.append(distance[i]).append('\n');
        }

        System.out.println(sb);
    }

    private static void dijkstraPriorityQueue(int start, int v) {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 1; i < v+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;
        pq.add(new Point(start, 0));

        while (!pq.isEmpty()) {
            Point now = pq.poll();

            int u = now.getX();

            if (distance[u] < now.getY()) continue;

            for (Point p: graph.get(u)) {
                if (distance[p.getX()] > distance[u] + p.getY()) {
                    distance[p.getX()] = distance[u] + p.getY();
                    pq.add(new Point(p.getX(), distance[p.getX()]));
                }
            }
        }
    }

    private static void dijkstra(int start, int v) {

        for (int i = 1; i < v+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;

        for (int i = 1; i < v; i++) {
            int min = getMinimum(v);
            if (min == -1)
                continue;

            visited[min] = true;

            for (Point p: graph.get(min)) {
                if (!visited[p.getX()] && distance[p.getX()] > distance[min] + p.getY()) {
                    distance[p.getX()] = distance[min] + p.getY();
                }
            }
        }
    }

    private static int getMinimum(int v) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 1; i < v+1; i++) {
            if (distance[i] < minDistance && !visited[i]) {
                minDistance = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}

class Point implements Comparable<Point> {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public int compareTo(Point other) {
        return this.y - other.y;
    }
}
