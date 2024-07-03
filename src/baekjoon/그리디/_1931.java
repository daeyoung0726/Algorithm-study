package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class _1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        StringTokenizer str;


        for (int i = 0; i < N; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(str.nextToken());
            arr[i][1] = Integer.parseInt(str.nextToken());
        }

        Comparator<int[]> cmp = (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        };

        Arrays.sort(arr, cmp);

        int count = 1;
        int endTime = arr[0][1];

        for (int i = 1; i < N; i++) {
            if (arr[i][0] >= endTime) {
                count++;
                endTime = arr[i][1];
            }
        }
        System.out.println(count);
    }
}