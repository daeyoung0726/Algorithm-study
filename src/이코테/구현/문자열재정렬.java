package 이코테.구현;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 문자열재정렬 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        List<Character> characters = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                integers.add(c - '0');
            } else {
                characters.add(c);
            }
        }

        Collections.sort(characters);

        StringBuilder sb = new StringBuilder();
        for (Character c: characters) {
            sb.append(c);
        }
        int sum = 0;

        for (int i: integers) {
            sum += i;
        }
        sb.append(sum);
        System.out.println(sb);
    }
}
