package baekjoon.문제집.코테대비;

// 고장난 번호 체크
// dfs로 for문에서 번호 다 돌고, 길이가 같으면 for문은 그만

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _1107 {

    private static boolean[] broken;
    private static int min = Integer.MAX_VALUE;
    private static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        num = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        broken = new boolean[10];

        min = Math.abs(num - 100);    // 시작이 100이므로, 이걸 최소로 설정

        if (n != 0) {
            StringTokenizer str = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                broken[Integer.parseInt(str.nextToken())] = true;
            }
        }

        if (num == 100) {
            System.out.println(0);
            return;
        }

        dfs(0, 0);
        System.out.println(min);
    }

    private static void dfs(int depth, int n) {

        for (int i = 0; i < 10; i++) {
            if (!broken[i]) {
                int newNum = 10 * n + i;
                int count = Math.abs(num - newNum) + String.valueOf(newNum).length();
                min = Math.min(min, count);

                if (depth < 6) {
                    dfs(depth + 1, newNum);
                }
            }
        }
    }
}