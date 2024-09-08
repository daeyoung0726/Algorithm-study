package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _12851 {

    private static int num;
    private static int min = Integer.MAX_VALUE;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int to = Integer.parseInt(str.nextToken());
        int from = Integer.parseInt(str.nextToken());

        num = 0;
        visited = new int[100001];

        bfs(to, from);

        System.out.println(min);
        System.out.println(num);
    }

    private static void bfs(int to, int from) {
        Queue<Integer> queue = new LinkedList<>();

        visited[to] = 1;
        queue.add(to);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == from) {
                if (min == visited[now] - 1) {
                    num++;
                }
                if (visited[now] - 1 < min) {
                    num = 1;
                    min = visited[now] - 1;
                }
            }

            int[] next = { now - 1, now + 1, now * 2};

            for (int i = 0; i < 3; i++) {
                if (next[i] >= 0 && next[i] <= 100000) {
                    if (visited[next[i]] == 0 || visited[next[i]] == visited[now] + 1) {
                        visited[next[i]] = visited[now] + 1;
                        queue.add(next[i]);
                    }
                }
            }
        }
    }
}