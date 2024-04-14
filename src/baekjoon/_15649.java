package baekjoon;

import java.util.Scanner;

public class _15649 {

    private static boolean[] visit;
    private static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        visit = new boolean[N];
        arr = new int[M];

        dfs(N, M, 0);
    }

    private static void dfs(int N, int M, int depth) {

        if(depth == M) {
            for(int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[depth] = i+1;
                dfs(N, M, depth + 1);
                visit[i] = false;
            }
        }
    }
}
