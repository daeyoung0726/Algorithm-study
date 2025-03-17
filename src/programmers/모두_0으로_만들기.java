package programmers;

// topology 이용.
// inDegree가 1인거부터 처리. 0으로 되는 값으로 처리해서 count.
// 인접 값은 그만큼 값 더하기

import java.util.*;

public class 모두_0으로_만들기 {
    private ArrayList<ArrayList<Integer>> graphs;
    private int[] inDegree;
    private long[] a;

    public long solution(int[] a, int[][] edges) {
        graphs = new ArrayList<>();

        int len = a.length;

        inDegree = new int[len];

        this.a = new long[len];
        for (int i = 0; i < len; i++) {
            this.a[i] = a[i];
        }

        long sum = 0;
        for (long val : this.a) {
            sum += val;
        }

        if (sum != 0)
            return -1;

        for (int i = 0; i < len; i++) {
            graphs.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            graphs.get(x).add(y);
            graphs.get(y).add(x);
            inDegree[x]++;
            inDegree[y]++;
        }

        return run(len);
    }

    private long run(int len) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            if (inDegree[i] == 1) {
                queue.add(i);
            }
        }

        long count = 0;

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            if (inDegree[idx] == 0) // 중심점
                continue;

            long num = a[idx];
            a[idx] = 0;
            count += Math.abs(num);

            for (int next : graphs.get(idx)) {
                a[next] += num;
                inDegree[next]--;

                if (inDegree[next] == 1)
                    queue.add(next);
            }
        }

        return count;
    }
}