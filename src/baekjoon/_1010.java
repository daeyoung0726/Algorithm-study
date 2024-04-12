package baekjoon;

import java.util.Scanner;

public class _1010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < num; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            sb.append(calc(N, M)).append('\n');
        }
        System.out.println(sb);
    }

    private static long calc(int N, int M) {
        long result = 1;
        for(int i = 1; i <= N; i++) {
            result *= M;
            result /= i;
            M--;
        }
        return result;
    }
}
