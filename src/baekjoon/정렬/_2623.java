package baekjoon.정렬;

// 위상정렬을 통해 확인.
// 만약 순서 다 출력 못하면 (잘못된 순서) 0출력. - 정해진 순서 팀 개수와 전체 팀 개수 비교하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2623 {
    private static ArrayList<ArrayList<Integer>> graphs;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        graphs = new ArrayList<>();
        inDegree = new int[n + 1];

        for (int i = 0; i < n + 1; i++)
            graphs.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int preNum = -1;
            str = new StringTokenizer(br.readLine(), " ");
            int nn = Integer.parseInt(str.nextToken());

            for (int j = 0; j < nn; j++) {
                int num = Integer.parseInt(str.nextToken());
                if (preNum != -1) {
                    graphs.get(preNum).add(num);
                    inDegree[num]++;
                }
                preNum = num;
            }
        }

        System.out.println(topology(n));
    }

    private static String topology(int n) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            count++;
            sb.append(now + "\n");

            for (int next : graphs.get(now)) {
                inDegree[next]--;

                if (inDegree[next] == 0)
                    queue.add(next);
            }
        }

        if (count < n)
            return "0";
        return sb.toString();
    }
}