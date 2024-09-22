package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _3568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String[] splitStr = str.split(" ");
        String prefix = splitStr[0];

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < splitStr.length; i++) {

            sb.append(prefix + reverseStr(splitStr[i])).append('\n');
        }

        System.out.println(sb);
    }

    private static String reverseStr(String s) {
        StringBuilder sb = new StringBuilder();
        int alphaIdx = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                alphaIdx = i;
            } else
                break;
        }
        for (int i = s.length() - 2; i > alphaIdx; i--) {
            if (s.charAt(i) == ']') {
                sb.append("[]");
                i--;
            } else
                sb.append(s.charAt(i));
        }

        sb.append(" " + s.substring(0, alphaIdx+1) + ";");
        return sb.toString();
    }
}