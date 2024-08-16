package 이코테.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 커리큘럼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] val = new int[n];

        StringTokenizer str;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(str.nextToken());
            val[i] = v;

            int sum = v;
            while (str.hasMoreTokens()) {
                int a = Integer.parseInt(str.nextToken());
                if (a == -1)
                    break;
                else {
                    sum = Math.max(sum, v + val[a-1]);
                }
            }
            val[i] = sum;
            sb.append(sum).append('\n');

        }

        System.out.println(sb);
    }
}

/* 아래 코드는 unionfind 사용할 시, 연관된거 못찾음. 위도 그렇겠지만.
public class 커리큘럼 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] costs = new int[n+1];

        UnionFind1 uf = new UnionFind1(n);
        StringBuilder sb = new StringBuilder();
        StringTokenizer str;

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int cost = Integer.parseInt(str.nextToken());
            costs[i+1] = cost;

            uf.setTime(i+1, cost);

            int count = 0;
            int sum = cost;

            while (str.hasMoreTokens()) {
                int a = Integer.parseInt(str.nextToken());
                count++;
                if (a == -1)
                    break;
                if (count > 1) {
                    sum = Math.max(sum, uf.union(i+1, a));
                } else {
                    sum = uf.union(i+1, a);
                }
                System.out.println(i+1 + " "+ sum);
                uf.setTime(i+1, cost);
            }

            uf.setTime(i+1, sum);
            sb.append(sum).append('\n');

        }

        System.out.println(sb);

    }
}

class UnionFind1 {

    private int[] parent;
    private int[] time;

    UnionFind1(int n) {
        this.parent = new int[n+1];
        this.time = new int[n+1];
        for (int i = 1; i < n+1; i++)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = x;
        return parent[x];
    }

    public int union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
            time[rootX] += time[rootY];
        }

        return time[rootX];
    }

    public void setTime(int n, int time) {
        this.time[n] = time;
    }
}*/
