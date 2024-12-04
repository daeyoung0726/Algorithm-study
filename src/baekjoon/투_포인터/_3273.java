package baekjoon.투_포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = n - 1;
        int count = 0;

        int result = Integer.parseInt(br.readLine());
        while (start < end) {
            if (arr[start] + arr[end] < result) {
                start++;
            } else if (arr[start] + arr[end] > result) {
                end--;
            } else {
                count++;
                start++;
                end--;
            }
        }

        System.out.println(count);
    }
}