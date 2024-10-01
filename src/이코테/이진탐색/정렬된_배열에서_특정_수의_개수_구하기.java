package 이코테.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정렬된_배열에서_특정_수의_개수_구하기 {

    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        arr = new int[n];

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        int left = leftBinarySearch(0, n-1, k);
        int right = rightBinarySearch(0, n-1, k);

        int result = (right - left > 0) ? (right - left) : -1;

        System.out.println(result);

    }

    private static int leftBinarySearch(int start, int end, int key) {

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= key)
                end = mid - 1;
            else
                start = mid + 1;
        }

        return end;
    }

    private static int rightBinarySearch(int start, int end, int key) {

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] > key)
                end = mid - 1;
            else
                start = mid + 1;
        }

        return end;
    }

}
