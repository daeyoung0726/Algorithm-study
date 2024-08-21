package 이코테.그리디;

import java.util.Scanner;

public class 문자열뒤집기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        int zero = 0;
        int one = 0;

        int before = 0;
        for (int i = 0; i < str.length(); i++) {
            int x = str.charAt(i) - '0';

            if (i == 0) {
                if (x == 0)
                    zero++;
                else
                    one++;
                before = x;
            }

            if (x != before) {
                if (x == 0)
                    zero++;
                else
                    one++;
            }

            before = x;
        }

        System.out.println(Math.min(zero, one));
    }
}
