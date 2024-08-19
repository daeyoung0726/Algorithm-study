package 이코테.그리디;

import java.util.Scanner;

public class 곱하기혹은더하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            int a = str.charAt(i) - '0';

            if (result <= 0) {
                result += a;
            } else
                result *= a;
        }
        System.out.println(result);
    }
}
