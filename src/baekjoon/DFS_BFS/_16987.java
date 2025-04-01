package baekjoon.DFS_BFS;

// 깰 순서 정하기. 처음 시작할 때, 배열로 값 담고 깨지는지 확인

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _16987 {
    private static List<int[]> eggs;
    private static int answer = 0;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        eggs = new ArrayList<>();
        arr = new int[n];

        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(str.nextToken());
            int w = Integer.parseInt(str.nextToken());

            eggs.add(new int[] {s, w});
        }

        dfs(n, 0);

        System.out.println(answer);
    }

    private static void dfs(int size, int start) {
        if (size == start) {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (eggs.get(i)[0] - arr[i] <= 0)
                    count++;
            }

            answer = Math.max(answer, count);
            return;
        }

        boolean check = false;

        for (int i = 0; i < size; i++) {
            if (i != start && arr[start] < eggs.get(start)[0] && arr[i] < eggs.get(i)[0]) {
                arr[start] += eggs.get(i)[1];
                arr[i] += eggs.get(start)[1];
                dfs(size, start + 1);
                arr[start] -= eggs.get(i)[1];
                arr[i] -= eggs.get(start)[1];
                check = true;
            }
        }

        if (!check)
            dfs(size, start + 1);
    }
}