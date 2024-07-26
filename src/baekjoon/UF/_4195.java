package baekjoon.UF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _4195 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            int size = 1;    // 배열 값 할당
            int m = Integer.parseInt(br.readLine());

            UnionFind2 uf = new UnionFind2(m);
            Map<String, Integer> hm = new HashMap<>();

            for (int j = 0; j < m; j++) {
                str = new StringTokenizer(br.readLine(), " ");
                String a = str.nextToken();
                String b = str.nextToken();

                if (!hm.containsKey(a))
                    hm.put(a, size++);

                if (!hm.containsKey(b))
                    hm.put(b, size++);

                int aIndex = hm.get(a);
                int bIndex = hm.get(b);

                sb.append(uf.union(aIndex, bIndex)).append('\n');
            }
        }

        System.out.println(sb);
    }
}

class UnionFind2 {
    private int[] parent;
    private int[] level;

    UnionFind2(int m) {
        this.parent = new int[2*m+1];
        this.level = new int[2*m+1];

        for (int i = 1; i < 2*m+1; i++) {
            parent[i] = i;
            level[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (level[rootA] > level[rootB]) {
                parent[rootB] = rootA;
                level[rootA] += level[rootB];
                return level[rootA];
            } else {
                parent[rootA] = rootB;
                level[rootB] += level[rootA];
                return level[rootB];
            }
        }

        return -1;
    }
}