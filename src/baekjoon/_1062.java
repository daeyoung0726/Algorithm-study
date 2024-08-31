package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1062 {

    private static boolean[] useAlpha;
    private static String[] str;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        k -= 5;

        if (k < 0) {
            System.out.println(0);
            return;
        }

        useAlpha = new boolean[26];
        str = new String[n];

        defalutUse();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            s = s.substring(4, s.length() - 4);  // 접두사 'anta', 접미사 'tica'
            str[i] = s;
        }

        dfs(0, 0, k);

        System.out.println(max);
    }

    private static void defalutUse() {
        useAlpha['a' - 'a'] = true;
        useAlpha['c' - 'a'] = true;
        useAlpha['i' - 'a'] = true;
        useAlpha['n' - 'a'] = true;
        useAlpha['t' - 'a'] = true;
    }

    private static void dfs(int idx, int start, int depth) {
        if (depth == 0) {
            int count = 0;
            for (String word : str) {
                boolean canRead = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!useAlpha[word.charAt(i) - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) count++;
            }
            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!useAlpha[i]) {
                useAlpha[i] = true;
                dfs(idx + 1, i + 1, depth - 1);
                useAlpha[i] = false;
            }
        }
    }
}
