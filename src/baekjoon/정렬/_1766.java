package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1766 {

    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        for (int i = 0; i <= n; i++) {
            graphs.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];

        int pre, post;
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");

            pre = Integer.parseInt(str.nextToken());
            post = Integer.parseInt(str.nextToken());

            graphs.get(pre).add(post);
            inDegree[post]++;
        }

        System.out.println(topology(inDegree, n));
    }

    private static String topology(int[] inDegree, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int now = pq.poll();

            sb.append(now + " ");

            for (int val: graphs.get(now)) {
                inDegree[val]--;

                if (inDegree[val] == 0)
                    pq.add(val);
            }
        }

        return sb.toString();
    }
}