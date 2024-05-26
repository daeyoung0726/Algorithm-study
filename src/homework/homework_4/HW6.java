package homework.homework_4;

import java.util.Scanner;

public class HW6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String X = sc.next();
        String Y = sc.next();

        int p = X.length();
        int q = Y.length();

        int[][] dp = new int[p + 1][q + 1];

        for (int i = 0; i <= p; i++) {      // i와 j는 문자열의 갯수를 뜻함.
            for (int j = 0; j <= q; j++) {
                if (i == 0 || j == 0) {     // i 또는 j의 문자열이 0이라는 뜻. 그래서 해당 부분 0으로 초기화.
                    dp[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {    // 같다면
                    dp[i][j] = dp[i - 1][j - 1] + 1;        // X의 i-1번째 문자열, Y의 j-1 문자열의 값에서 1을 더함.
                } else {                                            // 다르다면
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);    // X의 마지막 문자열 제외, Y의 마지막 문자열 제외 중 큰 값.
                }
            }
        }

        int a = p, b = q;
        while (a > 0 && b > 0) {
            if (X.charAt(a - 1) == Y.charAt(b - 1)) {
                sb.append(X.charAt(a - 1));
                a--;
                b--;
            } else if (dp[a - 1][b] >= dp[a][b - 1]) {
                a--;        //  a-1은 lcs에 포함되지 않아 값이 변화가 없으니깐 a--한다
            } else {
                b--;
            }
        }

        System.out.println(sb.reverse());
        System.out.println(dp[p][q]);
    }
}
