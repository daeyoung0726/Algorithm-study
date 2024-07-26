package baekjoon.UF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _1976 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        UnionFind1 uf = new UnionFind1(n);

        int[][] arr = new int[n][n];
        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while(str.hasMoreTokens()) {
                arr[i][j++] = Integer.parseInt(str.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1)
                    uf.union(i, j);
            }
        }

        int[] check = new int[m];
        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            check[i] = Integer.parseInt(str.nextToken()) - 1;
        }

        boolean isCheck = true;

        for (int i = 1; i < m; i++) {
            if (uf.find(check[i-1]) != uf.find(check[i])) {
                System.out.println("NO");
                isCheck = false;
                break;
            }
        }
        if (isCheck)
            System.out.println("YES");
    }
}

class UnionFind1 {
    private int[] parent;
    private int[] level;

    UnionFind1(int n) {
        this.parent = new int[n];
        this.level = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        for (; x != parent[x]; x = parent[x]);
        return parent[x];
    }

    /*public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }*/

    public void union(int a, int b) {
        int rootX = find(a);
        int rootY = find(b);

        if (rootX != rootY) {
            if (level[rootX] > level[rootY]) {
                parent[rootY] = rootX;
            } else if (level[rootX] < level[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                level[rootX] += 1;
            }
        }
    }
}

