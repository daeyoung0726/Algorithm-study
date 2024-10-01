package 이코테.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고정점_찾기 {

    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        System.out.println(binarySearch(0, n-1));
    }

    private static int binarySearch(int start, int end) {

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] > mid)
                end = mid - 1;
            else if (arr[mid] < mid)
                start = mid + 1;
            else
                return mid;
        }

        return -1;
    }
}
