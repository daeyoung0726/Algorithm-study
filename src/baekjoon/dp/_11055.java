package baekjoon.dp;

// 2중 반복문으로 해당 idx까지 dp를 또 함.
// 해당 idx까지의 최고 크기를 구함.
// 마지막에 전체를 돌며 가장 큰 값을 선택

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        System.out.println(dp(arr, n));
    }

    private static int dp(int[] arr, int n) {

        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = arr[i];

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && d[i] < d[j] + arr[i]) {
                    d[i] = d[j] + arr[i];
                }
            }
        }

        int max = d[0];

        for (int val: d) {
            max = Math.max(max, val);
        }

        return max;
    }
}