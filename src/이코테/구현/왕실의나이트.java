package 이코테.구현;

import java.util.Scanner;

public class 왕실의나이트 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int row = s.charAt(1) - '0';
        int col = s.charAt(0) - 'a' + 1;

        int count = 0;

        count += check(row, col);
        count += check(col, row);

        System.out.println(count);
    }

    private static int check(int x, int y) {
        if (x <= 2 || x >= 7) {
            if (y == 1 || y == 8)
                return 1;
            else
                return 2;
        } else {
            if (y == 1 || y == 8)
                return 2;
            else
                return 4;
        }
    }
}
