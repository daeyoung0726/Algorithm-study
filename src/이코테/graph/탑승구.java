package 이코테.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 탑승구 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        UnionFind2 uf = new UnionFind2(g);

        int[] airplane = new int[p];

        for (int i = 0; i < p; i++) {
            airplane[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < p; i++) {
            int root = uf.find(airplane[i]);

            if (root == 0)
                break;

            uf.union(root, root-1);
            count++;
        }

        System.out.println(count);
    }
}

class UnionFind2 {
    private int[] parent;

    UnionFind2(int n) {
        this.parent = new int[n+1];

        for (int i = 0; i < n+1; i++) {
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
            if (rootX < rootY)
                parent[rootY] = parent[rootX];
            else
                parent[rootX] = parent[rootY];
        }
    }
}


/*
public class 탑승구 {

    private static int[] airplane;
    private static boolean[] visited;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        visited = new boolean[g];
        airplane = new int[p];

        for (int i = 0; i < p; i++) {
            airplane[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, 0);

        System.out.println(max);
    }

    private static void dfs(int depth, int count) {
        if (depth == visited.length) {
            if (max < count)
                max = count;
        }

        for (int i = 0; i < airplane[depth]; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, count + 1);
                visited[i] = false;
            }
        }

        if (max < count)
            max = count;
    }
}
*/
