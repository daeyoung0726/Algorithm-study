package baekjoon.문제집.삼성;

// 큰 색종이 부터 확인.
// 인덱스를 돌아가며 차례대로 확인
// 브르투포스 진행

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _17136 {
    private static int[][] map;
    private static int answer = Integer.MAX_VALUE;

    private static final int[] usedPaper = {5, 5, 5, 5, 5};    // 차례대로 1, 2, 3, 4, 5

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(int x, int y, int count) {
        if (answer <= count)
            return;

        if (x == 10) {
            if (checkMap())
                answer = Math.min(answer, count);
            return;
        }

        if (y == 10) {
            dfs(x + 1, 0, count);
            return;
        }

        if (map[x][y] == 1) {
            for (int i = 4; i >= 0; i--) {
                if (x + (i + 1) <= 10 && y + (i + 1) <= 10
                        && usedPaper[i] > 0 && isPossible(x, y, i + 1)) {

                    cover(x, y, i + 1, 0);
                    usedPaper[i]--;
                    dfs(x, y + 1, count + 1);
                    cover(x, y, i + 1, 1);
                    usedPaper[i]++;
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }

    private static boolean isPossible(int x, int y, int n) {
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (map[i][j] == 0)
                    return false;
            }
        }

        return true;
    }

    private static void cover(int x, int y, int n, int value) {
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                map[i][j] = value;
            }
        }
    }

    private static boolean checkMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1)
                    return false;
            }
        }

        return true;
    }
}