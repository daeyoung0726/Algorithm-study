package baekjoon.문제집.코테대비;

import java.util.*;

public class _1748 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int answer = 0;
        int x = 1;
        int mod = 10;
        for (int i = 1; i <= n; i++) {
            if (i % mod == 0) {
                x++;
                mod *= 10;
            }

            answer += x;
        }

        System.out.println(answer);
    }
}