package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Work[] work = new Work[n+1];

        StringTokenizer str;
        for (int i = 1; i < n+1; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int day = Integer.parseInt(str.nextToken());
            int pay = Integer.parseInt(str.nextToken());

            work[i] = new Work(day, pay);
        }

        System.out.println(dp(work, n));
    }

    private static int dp(Work[] work, int n) {

        int[] d = new int[n+2];

        for (int i = 1; i <= n; i++) {
            Work w = work[i];

            int day = w.day;
            int pay = w.pay;
            if (i + day <= n+1) {
                d[i + day] = Math.max(d[i + day], d[i] + pay);
            }
            d[i+1] = Math.max(d[i], d[i+1]);
        }

        return d[n+1];
    }
}

class Work {
    int day;
    int pay;

    Work(int day, int pay) {
        this.day = day;
        this.pay = pay;
    }
}