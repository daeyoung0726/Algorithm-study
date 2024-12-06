package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2637 {
    private static ArrayList<ArrayList<int[]>> graphs;
    private static int[] inDegree;
    private static int[] requiredNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graphs = new ArrayList<>();
        inDegree = new int[n + 1];
        requiredNum = new int[n + 1];

        for (int i = 0; i <= n; i++)
            graphs.add(new ArrayList<>());

        StringTokenizer str;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            int num = Integer.parseInt(str.nextToken());

            graphs.get(x).add(new int[] {y, num});
            inDegree[y]++;
        }

        topologySort(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            if (graphs.get(i).isEmpty())
                sb.append(i + " " + requiredNum[i] + "\n");
        }

        System.out.println(sb);
    }

    private static void topologySort(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        requiredNum[n] = 1;    // 최종 결과물은 1개가 필요하기에

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int[] val : graphs.get(now)) {
                requiredNum[val[0]] += requiredNum[now] * val[1];
                inDegree[val[0]]--;

                if (inDegree[val[0]] == 0)
                    queue.add(val[0]);
            }
        }
    }
}