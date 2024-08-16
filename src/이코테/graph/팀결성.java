package 이코테.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 팀결성 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        UnionFind uf = new UnionFind(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int select = Integer.parseInt(str.nextToken());
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());

            if (select == 0) {
                uf.union(a, b);
            }
            if (select == 1) {
                if (uf.find(a) == uf.find(b))
                    sb.append("YES").append('\n');
                else
                    sb.append("NO").append('\n');
            }
        }

        System.out.println(sb);

    }
}

class UnionFind {

    private int[] parent;
    private int[] level;

    UnionFind(int n) {
        this.parent = new int[n+1];
        this.level = new int[n+1];

        for (int i = 1; i < n+1; i++) {
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
