package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int q = Integer.parseInt(br.readLine());

        int n = s.length();
        int[][] arr = new int[26][n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            arr[c - 'a'][i + 1] = 1;
        }


        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < n + 1; j++) {
                arr[i][j] += arr[i][j - 1];
            }
        }

        StringTokenizer str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            str = new StringTokenizer(br.readLine(), " ");

            char c = str.nextToken().charAt(0);
            int l = Integer.parseInt(str.nextToken());
            int r = Integer.parseInt(str.nextToken());

            sb.append(arr[c - 'a'][r+1] - arr[c - 'a'][l]).append('\n');
        }

        System.out.println(sb);
    }
}