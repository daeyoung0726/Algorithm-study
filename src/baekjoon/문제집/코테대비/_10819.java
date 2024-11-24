package baekjoon.문제집.코테대비;

// 브루트포스로 모든 경우의 수 구하기
// 주어진 크기와 depth가 같게 되면, 계산 후 계속 값을 확인

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _10819 {

    private static int[] arr;
    private static int[] calcArr;
    private static boolean[] visited;

    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        calcArr = new int[n];
        visited = new boolean[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        dfs(0);

        System.out.println(max);
    }

    private static void dfs(int depth) {
        if (depth == arr.length) {
            int sum = 0;
            for (int i = 0; i < calcArr.length - 1; i++) {
                sum += Math.abs(calcArr[i] - calcArr[i + 1]);
            }

            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                calcArr[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}