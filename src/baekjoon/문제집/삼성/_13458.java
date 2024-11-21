package baekjoon.문제집.삼성;

// 일단 총감독관을 먼저 할당. 방 돌면서 인원들을 다 빼버림
// 남은 인원이 1이상이라면 그에 맞게 할당

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        str = new StringTokenizer(br.readLine(), " ");

        int b = Integer.parseInt(str.nextToken());
        int c = Integer.parseInt(str.nextToken());

        long count = n;
        for (int i = 0; i < n; i++) {
            arr[i] -= b;

            if (arr[i] > 0) {
                count += arr[i] / c;
                if (arr[i] % c != 0)
                    count++;
            }
        }

        System.out.println(count);
    }
}