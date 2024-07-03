package 이코테.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드게임 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            int j = 0;
            str = new StringTokenizer(br.readLine(), " ");
            while (str.hasMoreTokens()) {
                arr[i][j++] = Integer.parseInt(str.nextToken());
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            Arrays.sort(arr[i]);
            if (max < arr[i][0])
                max = arr[i][0];
        }

        System.out.println(max);
    }
}
