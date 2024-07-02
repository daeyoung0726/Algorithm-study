package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1717 {
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
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            if (select == 0)
                uf.union(x, y);

            if (select == 1) {
                if (uf.find(x) != uf.find(y))
                    sb.append("NO\n");
                else
                    sb.append("YES\n");
            }
        }

        System.out.println(sb);
    }

}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size+1];
        this.rank = new int[size+1];

        for (int i = 0; i <= size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        for (; parent[x] != x; x = parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rootX > rootY)
                parent[rootY] = rootX;
            else if (rootX < rootY)
                parent[rootX] = rootY;
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}