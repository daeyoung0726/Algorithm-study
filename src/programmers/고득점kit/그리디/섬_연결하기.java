package programmers.고득점kit.그리디;

import java.util.*;

class 섬_연결하기 {
    public int solution(int n, int[][] costs) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);

        for (int i = 0; i < costs.length; i++) {
            int to = costs[i][0];
            int from = costs[i][1];
            int dis = costs[i][2];

            pq.add(new Node(to, from, dis));
        }

        int edgeCount = 0;
        int answer = 0;

        UnionFind uf = new UnionFind(n);

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (uf.find(now.to) != uf.find(now.from)) {
                uf.union(now.to, now.from);
                edgeCount++;
                answer += now.dis;
            }

            if (edgeCount == n - 1)
                break;
        }
        return answer;
    }
}

class Node {
    int to;
    int from;
    int dis;

    Node(int to, int from, int dis) {
        this.to = to;
        this.from = from;
        this.dis = dis;
    }
}

class UnionFind {
    private int[] parent;
    private int[] level;

    UnionFind(int n) {
        this.parent = new int[n + 1];
        this.level = new int[n + 1];

        for (int i = 1; i <= n; i++) {
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
            if (level[rootX] > level[rootY]) {
                parent[rootY] = rootX;
            } else if (level[rootX] < level[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                level[rootX]++;
            }
        }
    }
}