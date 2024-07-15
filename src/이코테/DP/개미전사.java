package 이코테.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개미전사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int i = 0;
        while (str.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(str.nextToken());
        }

        System.out.println(dp(arr));
    }

    private static int dp(int[] arr) {

        int[] d = new int[arr.length];

        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);    // 첫번째 혹은 두번째 창고 중 더 많은 것을 훔쳐야하기 때문.

        for (int i = 2; i < arr.length; i++) {
            d[i] = Math.max(d[i-1], d[i-2] + arr[i]);
        }
        return d[arr.length-1];
    }
}
