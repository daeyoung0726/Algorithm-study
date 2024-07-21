package 이코테.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전보 {

    private static ArrayList<ArrayList<Point>> graph = new ArrayList<>();
    private static int[] distance;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());
        int c = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            int z = Integer.parseInt(str.nextToken());
            graph.get(x).add(new Point(y, z));
        }

        dijkstraByPriorityQueue(c, n);
        int count = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++) {
            if (distance[i] != 10000000 && distance[i] != 0) {
                count++;
                max = Math.max(max, distance[i]);
            }
        }

        System.out.println(count + " " + max);
    }

    private static void dijkstra(int start, int n) {

        for (int i = 1; i < n+1; i++) {
            distance[i] = 10000000;
        }

        distance[start] = 0;
        visited[start] = true;

        for (Point p: graph.get(start)) {
            distance[p.getX()] = p.getY();
        }

        for (int i = 1; i < n; i++) {
            int min = getMinimum(n);
            if (min == -1)
                continue;

            visited[min] = true;

            for (Point p: graph.get(min)) {
                if (!visited[p.getX()])
                    distance[p.getX()] = Math.min(distance[p.getX()], distance[min] + p.getY());
            }
        }
    }

    private static int getMinimum(int n) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 1; i < n+1; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    private static void dijkstraByPriorityQueue(int start, int n) {
        PriorityQueue<Point> pq = new PriorityQueue<>();



        for (int i = 1; i < n+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;
        pq.add(new Point(start, 0));

        for (Point p: graph.get(start)) {
            distance[p.getX()] = p.getY();
        }

        while (!pq.isEmpty()) {
            Point now = pq.poll();

            for (Point p: graph.get(now.getX())) {
                int min = Math.min(distance[p.getX()], distance[now.getX()] + p.getY());
                pq.add(new Point(p.getX(), min));
            }
        }
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
        return this.x;
    }

    int getY() {
        return this.y;
    }


    @Override
    public int compareTo(Point o) {
        return Integer.compare(this.y, o.y);
    }
}