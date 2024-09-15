package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16953 {

    private static long b;
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        long a = Long.parseLong(str.nextToken());
        b = Long.parseLong(str.nextToken());

        dfs(a, 1);

        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    private static void dfs(long a, int count) {
        if (a == b) {
            min = Math.min(min, count);
            return;
        }

        if (a > b) {
            return;
        }

        dfs(2 * a, count+1);
        dfs(10 * a + 1, count+1);   // 이 부분때문에 long를 써야함. int는 21억까지임.
    }

    private static int bfs(int a) {
        Queue<long[]> queue = new LinkedList<>();
        queue.add(new long[] {a, 1});  // {현재 값, 연산 횟수}

        while (!queue.isEmpty()) {
            long[] current = queue.poll();
            long currentValue = current[0];
            long count = current[1];

            // 목표 값에 도달하면 연산 횟수 반환
            if (currentValue == b) {
                return (int) count;
            }

            // 값이 b를 초과하면 더 이상 진행하지 않음
            if (currentValue > b) {
                continue;
            }

            // 2를 곱하는 연산
            queue.add(new long[] {currentValue * 2, count + 1});

            // 1을 수의 가장 오른쪽에 추가하는 연산
            queue.add(new long[] {currentValue * 10 + 1, count + 1});
        }

        // 목표 값에 도달하지 못했을 때
        return -1;
    }
}