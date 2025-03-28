package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1039 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        System.out.println(bfs(n, k));
    }

    public static int bfs(int n, int k) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1000001][k + 1];

        queue.add(new int[] {n, 0});
        visited[n][0] = true;

        int result = -1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int num = now[0];
            int count = now[1];

            if (count == k) {
                result = Math.max(result, num);
                continue;
            }

            String temp = String.valueOf(num);
            int len = temp.length();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    int a = swap(temp, i, j);
                    if (a != -1 && !visited[a][count + 1]) {
                        queue.add(new int[] {a, count + 1});
                        visited[a][count + 1] = true;
                    }
                }
            }
        }

        return result;
    }

    private static int swap(String num, int idx1, int idx2) {
        if (idx1 == 0 && num.charAt(idx2) == '0')
            return -1;

        char temp1 = num.charAt(idx1);
        char temp2 = num.charAt(idx2);

        String result =
                num.substring(0, idx1) + String.valueOf(temp2) +
                        num.substring(idx1 + 1, idx2) + String.valueOf(temp1) +
                        num.substring(idx2 + 1);

        return Integer.parseInt(result);
    }
}