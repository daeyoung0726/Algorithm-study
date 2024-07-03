package 이코테.그리디;

import java.util.Scanner;

public class _1이될떄까지 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int count = 0;

        while (N > 1) {
            if (N % K == 0) {
                N /= K;
                count++;
            } else {
                int mod = N % K;
                N -= mod;
                count += mod;
                if (N == 0)
                    count--;
            }
        }

        System.out.println(count);
    }
}
