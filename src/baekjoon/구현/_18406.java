package baekjoon.구현;

import java.util.Scanner;

public class _18406 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        int half = s.length() / 2;

        String a = s.substring(0, half);
        String b = s.substring(half);

        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < half; i++) {
            leftSum += a.charAt(i) - '0';
            rightSum += b.charAt(i) - '0';
        }

        if (leftSum == rightSum)
            System.out.println("LUCKY");
        else
            System.out.println("READY");
    }
}