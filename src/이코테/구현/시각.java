package 이코테.구현;

import java.util.Scanner;

public class 시각 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int count = 0;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    if (check(i) || check(j) || check(k)) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static boolean check(int n) {
        String str = String.valueOf(n);
        return str.contains("3");
    }
}
