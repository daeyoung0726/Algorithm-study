package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2887 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Edge1> edges = new ArrayList<>(n);
        StringTokenizer str;

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            int z = Integer.parseInt(str.nextToken());

            edges.add(new Edge1(x, y, z, i));
        }

        PriorityQueue<Node1> nodes = new PriorityQueue<>((a, b) -> a.dis - b.dis);

        Collections.sort(edges, (a, b) -> a.x - b.x);
        for (int i = 0; i < n-1; i++) {
            Edge1 e1 = edges.get(i);
            Edge1 e2 = edges.get(i+1);
            nodes.add(new Node1(e1.idx, e2.idx, Math.abs(e1.x - e2.x)));
        }

        Collections.sort(edges, (a, b) -> a.y - b.y);
        for (int i = 0; i < n-1; i++) {
            Edge1 e1 = edges.get(i);
            Edge1 e2 = edges.get(i+1);
            nodes.add(new Node1(e1.idx, e2.idx, Math.abs(e1.y - e2.y)));
        }

        Collections.sort(edges, (a, b) -> a.z - b.z);
        for (int i = 0; i < n-1; i++) {
            Edge1 e1 = edges.get(i);
            Edge1 e2 = edges.get(i+1);
            nodes.add(new Node1(e1.idx, e2.idx, Math.abs(e1.z - e2.z)));
        }

        UnionFind4 uf = new UnionFind4(n);

        int edgeCount = 0;
        int sum = 0;
        int size = nodes.size();

        for (int i = 0; i < size; i++) {
            Node1 node = nodes.poll();

            if (uf.find(node.start) != uf.find(node.end)) {
                System.out.println(node.dis);
                uf.union(node.start, node.end);
                edgeCount++;
                sum += node.dis;
            }

            if (edgeCount == n-1)
                break;
        }

        System.out.println(sum);

    }

}

class Edge1 {
    int x;
    int y;
    int z;
    int idx;

    Edge1(int x, int y, int z, int idx) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}

class Node1 {
    int start;
    int end;
    int dis;

    Node1(int start, int end, int dis) {
        this.start = start;
        this.end = end;
        this.dis = dis;
    }
}

class UnionFind4 {
    private int[] parent;
    private int[] level;

    UnionFind4(int n) {
        this.parent = new int[n];
        this.level = new int[n];

        for (int i = 0; i < n; i++) {
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
                level[rootX]++;
            }
        }
    }
}