package 이코테.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 떡볶이 {

    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        arr = new int[N];

        str = new StringTokenizer(br.readLine(), " ");
        int i = 0;
        while (str.hasMoreTokens())
            arr[i++] = Integer.parseInt(str.nextToken());

        int max = -1;
        for (int val: arr)
            max = Math.max(max, val);

        int answer = binarySearch(0, max, M);

        System.out.println(answer);
    }

    private static int binarySearch(int start, int end, int M) {

        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            for (int val: arr) {
                sum += (val - mid > 0) ? val-mid : 0;
            }

            if (M == sum)
                return mid;

            if (M < sum)
                start = mid + 1;

            if (M > sum)
                end = mid - 1;
        }

        return -1;
    }
}
