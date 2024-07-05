package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        int[] arr = new int[N];
        int[] cumArr = new int[N+1];

        str = new StringTokenizer(br.readLine(), " ");
        int i = 0;
        while (str.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(str.nextToken());
        }

        cumArr[0] = 0;

        for (int j = 1; j < N+1; j++) {
            cumArr[j] = cumArr[j-1] + arr[j-1];
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < M; j++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            sb.append(cumArr[y] - cumArr[x-1]).append('\n');
        }
        System.out.println(sb);
    }
}
