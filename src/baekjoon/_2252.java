package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2252 {

    private static int[] inDegree;
    private static ArrayList<ArrayList<Integer>> graphs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        inDegree = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int low = Integer.parseInt(str.nextToken());
            int high = Integer.parseInt(str.nextToken());

            inDegree[high]++;
            graphs.get(low).add(high);
        }

        System.out.println(topology(n));
    }

    private static String topology(int n) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < n+1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now + " ");

            for (int high: graphs.get(now)) {
                inDegree[high]--;
                if (inDegree[high] == 0)
                    queue.add(high);
            }
        }

        return sb.toString();
    }
}