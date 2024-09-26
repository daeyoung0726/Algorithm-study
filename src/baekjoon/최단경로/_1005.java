package baekjoon.최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1005 {

    private static ArrayList<ArrayList<Integer>> graphs;
    private static int[] times;
    private static int[] inDegree;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            run();
        }
        br.close();
    }

    private static void run() throws IOException {
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        graphs = new ArrayList<>();
        times = new int[n + 1];
        inDegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        str = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            times[i] = Integer.parseInt(str.nextToken());
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            graphs.get(x).add(y);
            inDegree[y]++;
        }

        int w = Integer.parseInt(br.readLine());

        System.out.println(topologySort(n, w));
    }

    private static int topologySort(int n, int w) {
        Queue<Integer> queue = new LinkedList<>();

        int[] result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
            result[i] = times[i];
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == w) {
                return result[now];
            }

            for (int x: graphs.get(now)) {
                result[x] = Math.max(result[x], result[now] + times[x]);
                inDegree[x]--;
                if (inDegree[x] == 0)
                    queue.add(x);
            }
        }

        return -1;
    }
}