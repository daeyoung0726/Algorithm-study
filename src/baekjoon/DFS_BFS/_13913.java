package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _13913 {

    private static int[] visited;
    private static HashMap<Integer, Integer> sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int to = Integer.parseInt(str.nextToken());
        int from = Integer.parseInt(str.nextToken());

        visited = new int[100001];
        sequence = new HashMap<>();

        System.out.println(bfs(to, from));

        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        while (from != to) {
            int dir = sequence.get(from);
            list.add(from);
            if (dir == 0)
                from += 1;
            if (dir == 1)
                from -= 1;
            if (dir == 2)
                from /= 2;
        }
        list.add(to);

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i) + " ");
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int to, int from) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(to);
        visited[to] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == from) {
                return visited[now] - 1;
            }

            int[] next = { now * 2, now - 1, now + 1,  };

            for (int i = 0; i < 3; i++) {
                if (next[i] >= 0 && next[i] <= 100000 && visited[next[i]] == 0) {
                    visited[next[i]] = visited[now] + 1;
                    queue.add(next[i]);
                    sequence.put(next[i], i);
                }
            }
        }
        return -1;
    }
}