package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class _11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int i = 0;
        while (str.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(str.nextToken());
        }

        Arrays.sort(arr);
        int answer = arr[0];
        for (int j = 1; j < N; j++) {
            arr[j] += arr[j-1];
            answer += arr[j];
        }

        System.out.println(answer);
    }
}