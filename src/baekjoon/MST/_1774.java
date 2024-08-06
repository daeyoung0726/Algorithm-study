package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1774 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        ArrayList<Edge2> edges = new ArrayList<>(n);
        UnionFind2 uf = new UnionFind2(n);
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            edges.add(new Edge2(x, y, i+1));
        }

        ArrayList<Node2> nodes = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            Edge2 e1 = edges.get(i);
            for (int j = i+1; j < n; j++) {
                Edge2 e2 = edges.get(j);

                double distance = math(e1.x, e1.y, e2.x, e2.y);
                nodes.add(new Node2(e1.idx, e2.idx, distance));
            }
        }

        double answer = 0.0;
        int edgeCount = 0;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());

            Edge2 e1 = edges.get(a-1);
            Edge2 e2 = edges.get(b-1);
            if (uf.find(e1.idx) != uf.find(e2.idx)) {
                uf.union(e1.idx, e2.idx);
                edgeCount++;
            }

            if (edgeCount == n-1)
                break;
        }

        Comparator<Node2> cmp = (a, b) -> {
            return Double.compare(a.dis, b.dis);
        };

        Collections.sort(nodes, cmp);
        for (int i = 0; i < nodes.size(); i++) {

            if (edgeCount == n-1)
                break;

            Node2 node = nodes.get(i);

            if (uf.find(node.start) != uf.find(node.end)) {
                uf.union(node.start, node.end);
                answer += node.dis;
                edgeCount++;
            }
        }

        System.out.printf("%.2f", answer);
    }

    private static double math(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}

class Edge2 {
    int x;
    int y;
    int idx;

    Edge2(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}

class Node2 {
    int start;
    int end;
    double dis;

    Node2(int start, int end, double dis) {
        this.start = start;
        this.end = end;
        this.dis = dis;
    }
}

class UnionFind2 {
    private int[] parent;
    private int[] level;

    UnionFind2(int n) {
        this.parent = new int[n+1];
        this.level = new int[n+1];

        for (int i = 1; i < n+1; i++)
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