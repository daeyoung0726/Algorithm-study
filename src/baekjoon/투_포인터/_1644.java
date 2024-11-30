package baekjoon.투_포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _1644 {

    private static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        isPrime = new boolean[4000001];
        makePrime();

        System.out.println(findCount(n));
    }

    private static int findCount(int n) {

        if (n == 1)
            return 0;

        int count = 0;

        int sum = 0;
        int start = 0;
        int x = 0;
        int[] arr = new int[1000000];

        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                sum += i;
                arr[x++] = i;
            }

            while (sum > n) {
                sum -= arr[start++];
            }

            if (sum == n) {
                count++;
                sum -= arr[start++];
            }
        }

        return count;
    }

    private static void makePrime() {
        isPrime[0] = isPrime[1] = true;

        for (int i = 2; i < 4000001; i++) {
            for (int j = 2; i * j < 4000001; j++) {
                isPrime[i * j] = true;
            }
        }
    }
}