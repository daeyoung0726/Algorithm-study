package baekjoon.투_포인터;

// 이분탐색을 통해 두 값의 차이가 m이상인 것(그 중 가장 작은 것)을 찾는다.
// 정렬 후, 작은 수를 하나 잡고 하나는 이분탐색으로 찾는 것.
// 만약 이분 탐색하여 마지막 idx나온다면, 이분탐색은 그 이후로 진행하지 않고,
// 그 값으로 나머지 수에서 비교 (m보다 작을 때 까지)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        System.out.println(runCalc(arr, n, m));
    }

    private static int runCalc(int[] arr, int n, int m) {
        int answer = Integer.MAX_VALUE;

        int endIdx = 0;
        for (int i = 0; i < n; i++) {
            if (endIdx == n - 1) {    // 마지막 인덱스라면
                if (arr[endIdx] - arr[i] >= m)
                    answer = Math.min(answer, arr[endIdx] - arr[i]);
                else
                    break;

                if (isEquals(answer, m))
                    return answer;

                continue;
            }

            endIdx = binarySearch(arr, n, m + arr[i]);

            if (endIdx >= n)
                break;

            if (arr[endIdx] - arr[i] >= m)
                answer = Math.min(answer, arr[endIdx] - arr[i]);

            if (isEquals(answer, m))
                return answer;
        }

        return answer;
    }

    private static int binarySearch(int[] arr, int n, int m) {

        int ro = 0;
        int hi = n - 1;

        while (ro <= hi) {
            int mid = (ro + hi) / 2;

            if (arr[mid] == m)
                return mid;

            if (arr[mid] > m)
                hi = mid - 1;

            if (arr[mid] < m)
                ro = mid + 1;
        }

        return ro == n ? n - 1 : ro;    // 모든 수가 m보다 크다면 ro는 밖으로 나가기에
    }

    private static boolean isEquals(int answer, int m) {
        return answer == m;
    }
}