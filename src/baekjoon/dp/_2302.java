package baekjoon.dp;

// 2 : 12, 21
// 3 : 123, 132, 213
// 4 : 1234, 1243, 1324, 2134, 2143
// 5 : 12345, 12354, 12435, 13245, 21345, 21354, 21435, 13254

import java.util.*;

public class _2302 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[m];

        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }

        List<Integer> list = new ArrayList<>();

        int start = 1;
        for (int i = 0; i < m; i++) {
            int end = arr[i];
            list.add(end - start);
            start = end + 1;
        }

        list.add(n - start + 1);

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int sum = 1;

        for (int x: list) {
            sum *= dp[x];
        }

        System.out.println(sum);
    }
}