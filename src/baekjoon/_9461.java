package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9461 {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            dp(value);
        }
        System.out.println(sb);
    }

    private static void dp(int value) {
        if(value < 4) {    // value가 3까지는 1을 반환
            sb.append(1).append('\n');
            return;
        }
        long[] arr = new long[value];
        arr[0] = arr[1] = arr[2] = 1;

        for(int i = 3; i < value; i++) {
            arr[i] = arr[i-3] + arr[i-2];
        }
        sb.append(arr[value-1]).append('\n');
    }
}
