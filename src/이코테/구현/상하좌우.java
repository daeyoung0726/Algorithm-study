package 이코테.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 상하좌우 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int row = 1;
        int col = 1;

        while (str.hasMoreTokens()) {

            switch (str.nextToken()) {
                case "L" :
                    if (check(col, -1)) {
                        col -= 1;
                    }
                    break;
                case "R":
                    if (check(col, 1)) {
                        col += 1;
                    }
                    break;
                case "U":
                    if (check(row, -1)) {
                        row -= 1;
                    }
                    break;
                case "D":
                    if (check(row, 1)) {
                        row += 1;
                    }
                    break;
                default:
                    throw new RuntimeException("잘못된 입력");
            }
        }

        System.out.println(row + " " + col);
    }

    private static boolean check(int location, int x) {
        if (location == 1 && x == -1) {
            return false;
        } else if (location == N && x == 1) {
            return false;
        } else {
            return true;
        }
    }
}
