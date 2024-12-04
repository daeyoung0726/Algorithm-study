package baekjoon.투_포인터;

// 반별로 정렬
// 첫열 최대, 최소 차이 구하고 계산 후, 가장 작은 값을 인덱스 하나 올리기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int[][] team = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                team[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        for (int i = 0; i < n; i++)
            Arrays.sort(team[i]);

        int[] idx = new int[n];

        int min = Integer.MAX_VALUE;

        while (true) {
            int minVal = Integer.MAX_VALUE;;
            int maxVal = Integer.MIN_VALUE;
            int minIdx = -1;

            for (int i = 0; i < n; i++) {
                if (minVal > team[i][idx[i]]) {
                    minVal = team[i][idx[i]];
                    minIdx = i;
                }
                if (maxVal < team[i][idx[i]]) {
                    maxVal = team[i][idx[i]];
                }
            }

            min = Math.min(min, maxVal - minVal);

            idx[minIdx]++;

            if (idx[minIdx] >= m)
                break;
        }

        System.out.println(min);
    }
}