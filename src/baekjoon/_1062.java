package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1062 {

    private static boolean[] alpha;
    private static String[] word;
    private static int n;
    private static int k;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        k = Integer.parseInt(str.nextToken());
        k -= 5;
        if (k < 0) {    // 0은 빼줘야함. antatica는 가능하기에
            System.out.println(0);
            return;
        }

        alpha = new boolean[26];
        plusDefaultAlpha();
        word = new String[n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            word[i] = s.substring(4, s.length() - 4);
        }

        dfs(0, 0);

        System.out.println(max);
    }

    private static void plusDefaultAlpha() {
        alpha['a' - 'a'] = true;
        alpha['c' - 'a'] = true;
        alpha['i' - 'a'] = true;
        alpha['n' - 'a'] = true;
        alpha['t' - 'a'] = true;
    }

    private static void dfs(int start, int depth) {

        if (depth == k) {
            int count = 0;
            for (String w: word) {
                boolean check = true;
                for (int i = 0; i < w.length(); i++) {
                    if (!alpha[w.charAt(i) - 'a']) {
                        check = false;
                        break;
                    }
                }
                if (check)
                    count++;
            }
            max = Math.max(max, count);
            return;
        }


        for (int i = start; i < 26; i++) {
            if (!alpha[i]) {
                alpha[i] = true;
                dfs(i + 1, depth + 1);
                alpha[i] = false;
            }
        }
    }
}