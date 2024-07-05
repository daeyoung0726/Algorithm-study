package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());

        int[] arr = new int[N];

        str = new StringTokenizer(br.readLine(), " ");
        int i = 0;
        while (str.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(str.nextToken());
        }

        int max = 0;
        for (int j = 0; j < K; j++) {
            max += arr[j];
        }
        int check = max;
        for (int j = 1; j <= N-K; j++) {
            check -= arr[j-1];
            check += arr [j+K-1];
            if (check > max)
                max = check;
        }

        System.out.println(max);
    }
}
