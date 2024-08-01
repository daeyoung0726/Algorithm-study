package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(str.nextToken());
        int e = Integer.parseInt(str.nextToken());

        UnionFind uf = new UnionFind(v);
        ArrayList<Point> arr = new ArrayList<>(e);

        for (int i = 0; i < e; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            int dis = Integer.parseInt(str.nextToken());
            arr.add(new Point(x, y, dis));
        }

        Comparator<Point> cmp = (a, b) -> {
            return a.dis - b.dis;
        };

        Collections.sort(arr, cmp);

        int answer = 0;
        int edgeCount = 0;
        for (int i = 0; i < e; i++) {
            Point p = arr.get(i);

            if (uf.find(p.x) == uf.find(p.y))
                continue;

            uf.union(p.x, p.y);
            answer += p.dis;
            edgeCount++;

            if (edgeCount == v-1)
                break;

        }

        System.out.println(answer);
    }
}

class Point {
    int x;
    int y;
    int dis;

    Point(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}

class UnionFind {
    private int[] parent;
    private int[] level;

    UnionFind(int v) {
        this.parent = new int[v+1];
        this.level = new int[v+1];

        for (int i = 1; i < v+1; i++)
            parent[i] = i;
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