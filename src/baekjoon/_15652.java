package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15652 {
    private static int[] arr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        arr = new int[M];

        dfs(N, M, 0);

        System.out.println(sb);
    }

    private static void dfs(int N, int M, int depth) {

        if (depth == M) {
            for (int val : arr)
                sb.append(val).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            if (depth >= 1 && arr[depth-1] > i+1)
                continue;

            arr[depth] = i+1;
            dfs(N, M, depth+1);
        }
    }
}
