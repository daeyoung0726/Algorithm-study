package 이코테.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 큰수의법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int answer = 0;

        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());

        str = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[N];
        int i = 0;
        while (str.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(str.nextToken());
        }

        Arrays.sort(arr);

        int idx = arr.length;

        int count = M / (K + 1);
        int mod = M % (K + 1);

        int sum = arr[idx - 1] * K + arr[idx - 2];

        answer += count * sum;
        answer += mod * arr[idx - 1];


        System.out.println(answer);

    }
}
