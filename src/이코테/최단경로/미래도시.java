package 이코테.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 미래도시 {

    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        arr = new int[n+1][n+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                arr[i][j] = 10000;
            }
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        str = new StringTokenizer(br.readLine(), " ");

        int x = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        System.out.println(floyd(1, x, k, n));
    }

    private static int floyd(int start, int x, int k, int n) {

        for (int a = 1; a < n+1; a++) {
            for (int i = 1; i < n+1; i++) {
                if (a == i)
                    continue;
                for (int j = 1; j < n+1; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][a] + arr[a][j]);
                }
            }
        }
        if (arr[start][k] == 10000 || arr[k][x] == 10000)
            return -1;
        return arr[start][k] + arr[k][x];
    }
}


/*
public class 미래도시 {

    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();
    private static int[] distance;
    private static boolean[] visited;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }
        distance = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(str.nextToken());
            int v = Integer.parseInt(str.nextToken());

            graphs.get(u).add(v);
            graphs.get(v).add(u);
        }

        str = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());
        dijkstra(1, x, k);
    }

    private static void dijkstra(int start, int x, int k) {

        for (int i = 1; i < n + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;
        visited[start] = true;

        for (int j : graphs.get(start)) {
            distance[j] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            int min = getMinimum();
            if (min == -1) break;
            visited[min] = true;

            for (int j : graphs.get(min)) {
                if (!visited[j]) {
                    distance[j] = Math.min(distance[j], distance[min] + 1);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            System.out.println(distance[i]);
        }
    }

    private static int getMinimum() {

        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i] && minDistance > distance[i]) {
                minDistance = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
*/
