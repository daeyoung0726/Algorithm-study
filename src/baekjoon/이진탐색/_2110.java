package baekjoon.이진탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class _2110 {

    private static int[] location;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int c = Integer.parseInt(str.nextToken());

        location = new int[n];

        for (int i = 0; i < n; i++) {
            location[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(location);

        int low = 1;
        int high = location[n-1] - location[0] + 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (checkCount(mid, n, c)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(low - 1);
    }

    private static boolean checkCount(int minDis, int n, int c) {

        int count = 1;
        int lastLocation = location[0];

        for (int i = 0; i < n; i++) {
            int nowLocation = location[i];

            if (nowLocation - lastLocation >= minDis) {
                count++;
                lastLocation = nowLocation;
            }

            if (count >= c)
                return true;
        }

        return false;
    }
}