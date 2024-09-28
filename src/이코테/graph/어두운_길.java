package 이코테.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 어두운_길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);

        UnionFind3 uf = new UnionFind3(n);

        int total = 0;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            int z = Integer.parseInt(str.nextToken());
            pq.add(new Node(x, y, z));
            total += z;
        }

        int edgeCount = 0;

        int sum = 0;
        for (int i = 0; i < m; i++) {
            Node node = pq.poll();

            if (uf.find(node.start) != uf.find(node.end)) {
                uf.union(node.start, node.end);
                edgeCount++;
                sum += node.dis;
            }

            if (edgeCount == n - 1)
                break;
        }

        System.out.println(total - sum);
    }
}

class Node {
    int start;
    int end;
    int dis;

    Node(int start, int end, int dis) {
        this.start = start;
        this.end = end;
        this.dis = dis;
    }
}

class UnionFind3 {
    private int[] parent;
    private int[] level;

    UnionFind3(int n) {
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