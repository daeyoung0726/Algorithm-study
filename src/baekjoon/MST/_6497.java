package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _6497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer str = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(str.nextToken());
            int n = Integer.parseInt(str.nextToken());

            if (m == 0 && n == 0) break;

            UnionFind3 uf = new UnionFind3(m);
            ArrayList<Edge3> edges = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                str = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(str.nextToken());
                int y = Integer.parseInt(str.nextToken());
                int z = Integer.parseInt(str.nextToken());

                edges.add(new Edge3(x, y, z));
            }

            Comparator<Edge3> cmp = (a, b) -> {
                return a.dis - b.dis;
            };

            Collections.sort(edges, cmp);

            int answer = 0;

            for (int i = 0; i < n; i++) {
                Edge3 e = edges.get(i);

                if (uf.find(e.x) != uf.find(e.y)) {
                    uf.union(e.x, e.y);
                } else {
                    answer += e.dis;
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }
}

class Edge3 {
    int x;
    int y;
    int dis;

    Edge3(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}

class UnionFind3 {
    private int[] parent;
    private int[] level;

    UnionFind3(int m) {
        this.parent = new int[m];
        this.level = new int[m];
        for (int i = 0; i < m; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

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
