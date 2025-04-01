package baekjoon.DFS_BFS;

// bfs로 진행.
// 1 ~ 6 값 넣고, 만약 뱀의 위치라면 무시. 사다리라면 올리기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _16928 {
    private static Map<Integer, Integer> ladders;
    private static Map<Integer, Integer> snakes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ladders = new HashMap<>();
        snakes = new HashMap<>();

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            ladders.put(x, y);
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            snakes.put(x, y);
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        queue.add(new int[] {1, 0});
        visited[1] = true;

        int[] dice = {1, 2, 3, 4, 5, 6};

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int num = now[0];
            int count = now[1];

            if (num == 100)
                return count;

            for (int i = 0; i < 6; i++) {
                int nx = num + dice[i];

                if (nx > 100 || visited[nx])
                    continue;

                if (ladders.containsKey(nx)) {
                    queue.add(new int[] {ladders.get(nx), count + 1});
                } else if (snakes.containsKey(nx)) {
                    queue.add(new int[] {snakes.get(nx), count + 1});
                } else {
                    queue.add(new int[] {nx, count + 1});
                }
                visited[nx] = true;
            }
        }

        return -1;
    }
}