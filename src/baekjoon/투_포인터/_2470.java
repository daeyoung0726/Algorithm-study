package baekjoon.투_포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class _2470 {
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
        int min = Integer.MAX_VALUE;
        int key1 = arr[0];
        int key2 = arr[n - 1];

        int result = 0;
        while (start < end) {
            result = arr[start] + arr[end];

            if (Math.abs(min) > Math.abs(result)) {
                key1 = arr[start];
                key2 = arr[end];
                min = result;
            }

            if (result < 0) {
                start++;
            } else if (result > 0) {
                end--;
            } else {
                break;
            }
        }

        System.out.println(key1 + " " + key2);
    }
}