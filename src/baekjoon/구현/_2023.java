package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _2023 {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dfs(0, 0);
    }

    private static void dfs(int num, int length) {
        if (length == n) {
            if (!isPrime(num)) {
                System.out.println(num);
                return;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (!isPrime(num * 10 + i)) {
                dfs(num * 10 + i, length + 1);
            }
        }
    }

    private static boolean isPrime(int n) {

        if (n <= 1)
            return true;
        if (n == 2)
            return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return true;
        }
        return false;
    }
}

/*
package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _2023 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int start = (int) Math.pow(10, n-1);
        int end = (int) Math.pow(10, n);
        StringBuilder sb = new StringBuilder();
        int failedNumMod = 0;
        int failedNum = 0;
        for (int i = start; i < end; i++) {
            boolean isPrime = true;

            if (failedNum != 0) {
                if ((i / failedNumMod) == failedNum) {
                    continue;
                }
            }
            for (int j = n-1; j >= 0; j--) {
                int mod = (int) Math.pow(10, j);
                int num = i / mod;

                if (!check(num)) {
                    failedNumMod = mod;
                    failedNum = num;
                    isPrime = false;
                    break;
                }
            }

            if (isPrime)
                sb.append(i + "\n");
        }
        System.out.println(sb);
    }

    private static boolean check(int n) {

        if (n == 1)
            return false;
        if (n == 2)
            return true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}*/
