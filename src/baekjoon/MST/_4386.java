package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _4386 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Edge> edges = new ArrayList<>(n);

        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            double x = Double.parseDouble(str.nextToken());
            double y = Double.parseDouble(str.nextToken());

            edges.add(new Edge(x, y, i));
        }

        UnionFind1 uf = new UnionFind1(n);

        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            Edge e1 = edges.get(i);
            for (int j = i+1; j < n; j++) {
                Edge e2 = edges.get(j);
                double dis = math(e1.x, e1.y, e2.x, e2.y);
                nodes.add(new Node(e1, e2, dis));       // 넣을때 Edge말고 edge.idx를 넣으면 위치 확인 가능.
            }
        }

        Comparator<Node> cmp = (a, b) -> {
            return Double.compare(a.dis, b.dis);
        };

        Collections.sort(nodes, cmp);

        double answer = 0.0;
        int edgeCount = 0;
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            if (uf.find(node.e1.idx) != uf.find(node.e2.idx)) {
                uf.union(node.e1.idx, node.e2.idx);
                answer += node.dis;
                edgeCount++;
            }

            if (edgeCount == n-1)
                break;
        }

        System.out.printf("%.2f", answer);
    }

    private static double math(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}

class Edge {
    double x;
    double y;
    int idx;

    Edge(double x, double y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}

class Node {
    Edge e1;        // Edge말고 int start
    Edge e2;        // int end
    double dis;

    Node(Edge e1, Edge e2, double dis) {
        this.e1 = e1;
        this.e2 = e2;
        this.dis = dis;
    }
}

class UnionFind1 {
    private int[] parent;
    private int[] level;

    UnionFind1(int n) {
        this.parent = new int[n];
        this.level = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int find(int idx) {
        if (parent[idx] != idx)
            parent[idx] = find(parent[idx]);
        return parent[idx];
    }

    public void union(int idx1, int idx2) {
        int root1 = find(idx1);
        int root2 = find(idx2);

        if (root1 != root2) {
            if (level[root1] > level[root2]) {
                parent[root2] = root1;
            } else if (level[root1] < level[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                level[root1] += 1;
            }
        }
    }
}