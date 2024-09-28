package 이코테.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 여행_계획 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }
        UnionFind1 uf = new UnionFind1(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    uf.union(i+1, j+1);
                }
            }
        }

        str = new StringTokenizer(br.readLine(), " ");
        List<Integer> list = new ArrayList<>();

        while (str.hasMoreTokens())
            list.add(Integer.parseInt(str.nextToken()));

        boolean check = true;
        for (int i = 0; i < list.size()-1; i++) {
            if (uf.find(list.get(i)) != uf.find(list.get(i+1))) {
                check = false;
                break;
            }
        }

        if (check)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}

class UnionFind1 {
    private int[] parent;
    private int[] level;

    UnionFind1(int n) {
        parent = new int[n+1];
        level = new int[n+1];

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
                level[rootX]++;
            }
        }
    }
}
