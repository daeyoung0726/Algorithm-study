package baekjoon.문제집.삼성;

// 백트래킹 사용

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _16637 {
    private static char[] arr;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new char[n];

        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i);
        }

        dfs(0, arr[0] - '0');

        System.out.println(max);
    }

    private static void dfs(int start, int sum) {
        if (start >= arr.length - 1) {
            max = Math.max(max, sum);
            return;
        }

        int calc = calculate(sum, arr[start + 2] - '0', arr[start + 1]);
        dfs(start + 2, calc);

        if (start + 4 < arr.length) {
            int num = calculate(arr[start + 2] - '0', arr[start + 4] - '0', arr[start + 3]);
            int result = calculate(sum, num, arr[start + 1]);
            dfs(start + 4, result);
        }
    }


    private static int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -1;
    }
}