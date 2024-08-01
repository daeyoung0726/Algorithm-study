package baekjoon.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringTokenizer str;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(str.nextToken());
            int m = Integer.parseInt(str.nextToken());


            for (int j = 0; j < m; j++) {
                br.readLine();

            }

            sb.append(n-1).append('\n');
        }

        System.out.println(sb);
    }
}