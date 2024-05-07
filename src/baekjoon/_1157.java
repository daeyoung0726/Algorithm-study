package baekjoon;

import java.util.Scanner;

public class _1157 {
    public static void main(String[] args) {
        int[] english = new int[26];

        Scanner sc = new Scanner(System.in);

        String voca = sc.next();

        for(int i = 0; i < voca.length(); i++) {
            char x = voca.charAt(i);

            if(x >= 'a' && x <= 'z')
                english[x-'a']++;
            if(x >= 'A' && x <='Z')
                english[x-'A']++;

        }
        int max = english[0];
        int index = 0;
        int count = 0;
        for(int i = 1; i < english.length; i++) {
            if(max == english[i])
                count++;
            if (max < english[i]) {
                max = english[i];
                index = i;
                count = 0;
            }
        }
        char result = (count == 0) ? (char) ('A' + index) : '?';
        System.out.println(result);
    }
}
