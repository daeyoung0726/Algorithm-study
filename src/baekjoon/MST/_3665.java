package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _3665 {

    private static int[] lastYearRank;
    private static int[] inDegree;
    private static boolean[][] dir;
    private static Queue<Integer> queue;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer str;

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            lastYearRank = new int[n+1];
            inDegree = new int[n+1];
            dir = new boolean[n+1][n+1];

            str = new StringTokenizer(br.readLine(), " ");

            // 입력되는 것은 팀의 번호. idx가 팀의 순위
            for (int j = 1; j < n+1; j++) {
                lastYearRank[j] = Integer.parseInt(str.nextToken());
            }

            // 높은 등수(1)의 팀이 낮은 등수(2)의 팀을 가리키도록.
            for (int j = 1; j < n; j++) {
                for (int k = j+1; k < n+1; k++) {
                    dir[lastYearRank[j]][lastYearRank[k]] = true;
                    inDegree[lastYearRank[k]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());

            for (int j = 0; j < m; j++) {
                str = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(str.nextToken());
                int b = Integer.parseInt(str.nextToken());

                swap(a, b);
            }

            queue = new LinkedList<>();

            for (int j = 1; j < n+1; j++) {
                if (inDegree[j] == 0)
                    queue.add(j);
            }

            topology(n);
        }
        System.out.println(sb);
    }

    private static void swap(int a, int b) {

        if (dir[a][b]) {    // a가 b보다 더 높은 순위
            dir[a][b] = false;
            dir[b][a] = true;
            inDegree[a]++;
            inDegree[b]--;
        } else {            // b가 a보다 더 높은 순위
            dir[a][b] = true;
            dir[b][a] = false;
            inDegree[a]--;
            inDegree[b]++;
        }
    }

    private static void topology(int n) {

        int[] result = new int[n];

        for (int i = 1; i < n+1; i++) {
            if (queue.size() == 0) {    // cycle 발생
                sb.append("IMPOSSIBLE").append('\n');
                return;
            }
            if (queue.size() > 1) {    // 확실한 순위를 찾을 수 없음
                sb.append("?").append('\n');
                return;
            }

            int now = queue.poll();
            result[i-1] = now;
            for (int j = 1; j < n+1; j++) {    // 가리키는 (팀 now보다 순위가 낮은) 팀 확인
                if (dir[now][j]) {
                    inDegree[j]--;

                    if (inDegree[j] == 0)
                        queue.add(j);
                }
            }
        }

        for (int rank: result) {
            sb.append(rank + " ");
        }
        sb.append('\n');
    }
}