package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _1932 {
    private static ArrayList<Integer>[] list;
    private static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n];
        StringTokenizer st;
        int j;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            list[i] = new ArrayList<>(i+1);
            while(st.hasMoreTokens())
                list[i].add(Integer.parseInt(st.nextToken()));
        }
        dfs(list[0].get(0), n, 0, 0);
        System.out.println(MAX);
    }

    private static void dfs(int sum, int n, int dep, int idx) {
        if(n == dep+1) {
            MAX = Math.max(sum, MAX);
            return;
        }

        dfs(sum+list[dep+1].get(idx), n, dep+1, idx);
        dfs(sum+list[dep+1].get(idx+1), n, dep+1, idx+1);
    }
}
