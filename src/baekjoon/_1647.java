package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1647 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        UnionFind uf = new UnionFind(n);

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            int c = Integer.parseInt(str.nextToken());

            pq.add(new Point(a, b, c));
        }

        int answer = 0;
        int edgeCount = 0;
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Point p = pq.poll();

            if (uf.find(p.a) != uf.find(p.b)) {
                uf.union(p.a, p.b);
                answer += p.c;
                edgeCount++;
            }

            if (edgeCount == n - 1) {
                answer -= p.c;
                break;
            }
        }

        System.out.println(answer);
    }
}

class Point implements Comparable<Point> {
    int a;
    int b;
    int c;

    Point(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Point o) {
        return this.c - o.c;
    }
}

class UnionFind {
    private int[] parent;
    private int[] level;

    UnionFind(int n) {
        this.parent = new int[n+1];
        this.level = new int[n+1];

        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (level[rootX] > level[rootY])
                parent[rootY] = rootX;
            else if (level[rootX] < level[rootY])
                parent[rootX] = rootY;
            else {
                parent[rootY] = rootX;
                level[rootX] += 1;
            }
        }
    }
}