package baekjoon;

import java.util.Scanner;

public class _1789 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long s = sc.nextLong();
        long sum = 0;
        long n = 1;
        while (n + sum <= s) {
            sum += n++;
        }

        System.out.println(--n);
    }
}