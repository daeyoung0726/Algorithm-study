package baekjoon.DFS_BFS;

// 수빈이가 이동한 칸의 count - 동생이 그 칸에 이동했을때 count % 2 를 했을때 0이어야함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17071 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        System.out.println(bfs(n, m));
    }

    private static int bfs(int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[500_001][2];

        visited[n][0] = true;
        queue.add(new int[] {n, 1});

        if (m == n)
            return 0;

        int pre = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int time = now[1];

            int nextTime = (time % 2) == 0 ? 0 : 1;

            int[] next = {x + 1, x - 1, x * 2};

            for (int i = 0; i < 3; i++) {
                if (next[i] <= 0 || next[i] > 500000 || visited[next[i]][nextTime])
                    continue;

                visited[next[i]][nextTime] = true;
                queue.add(new int[] {next[i], time + 1});
            }

            if (pre != time) {
                m += pre;
                pre = time;
            }

            if (m + time > 500_000) {
                return -1;
            }

            if (visited[m + time][nextTime]) {
                return time;
            }
        }

        return -1;
    }
}