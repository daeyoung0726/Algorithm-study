package baekjoon.문제집.코테대비;

// 전체 가능성 다 구하기
// 모음 1, 자음 2 이상인지 확인
// 단어는 오름차순이어야함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1759 {

    private static StringBuilder sb = new StringBuilder();

    private static int l;
    private static int c;
    private static char[] chars;
    private static final char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        l = Integer.parseInt(str.nextToken());
        c = Integer.parseInt(str.nextToken());

        chars = new char[c];

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < c; i++) {
            chars[i] = str.nextToken().charAt(0);
        }

        Arrays.sort(chars);

        dfs(0, 0, "");
        System.out.println(sb);
    }

    private static void dfs(int start, int depth, String word) {
        if (l == depth) {

            int voCount = 0;
            int consCount = 0;
            boolean check;
            for (int i = 0; i < word.length(); i++) {
                check = false;
                for (int j = 0; j < vowels.length; j++) {
                    if (word.charAt(i) == vowels[j]) {
                        voCount++;
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    consCount++;
                }

                if (voCount >= 1 && consCount >= 2) {
                    sb.append(word + "\n");
                    break;
                }
            }

            return;
        }

        for (int i = start; i < c; i++) {
            String newWord = word + chars[i];
            dfs(i + 1, depth + 1, newWord);
        }
    }
}