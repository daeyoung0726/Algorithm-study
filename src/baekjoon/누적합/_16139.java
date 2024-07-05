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
        StringTokenizer str;
        StringBuilder sb = new StringBuilder();
        int count;

        for (int i = 0; i < q; i++) {
            count = 0;
            str = new StringTokenizer(br.readLine(), " ");
            char x = str.nextToken().charAt(0);
            int l = Integer.parseInt(str.nextToken());
            int r = Integer.parseInt(str.nextToken());

            for (int j = l; j <= r; j++) {
                if (x == s.charAt(j))
                    count++;
            }
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }
}
