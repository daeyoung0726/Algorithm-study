package programmers.고득점kit.DFS_BFS;

import java.util.*;

class 타겟_넘버 {

    private int len;
    private int[] n;
    private int count = 0;

    public int solution(int[] numbers, int target) {

        len = numbers.length;
        n = numbers;
        dfs(0, 0, target);
        //return bfs(target);
        return count;
    }

    private void dfs(int idx, int sum, int target) {
        if (idx == len) {
            if (sum == target) {
                count++;
            }
            return;
        }

        dfs(idx + 1, sum + n[idx], target);
        dfs(idx + 1, sum - n[idx], target);
    }

    private int bfs(int target) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {n[0], 1});
        queue.add(new int[] {-n[0], 1});

        int count = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int num = now[0];
            int depth = now[1];

            if (depth == len) {
                if (num == target) {
                    count++;
                }
                continue;
            }

            queue.add(new int[] {num + n[depth], depth + 1});
            queue.add(new int[] {num - n[depth], depth + 1});
        }

        return count;
    }
}