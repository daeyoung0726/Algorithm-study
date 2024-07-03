package baekjoon.그리디;

import java.util.*;

public class _1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.next();

        String[] arr = text.split("\\-");

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String[] num = arr[i].split("\\+");

            for (int j = 0; j < num.length; j++) {
                result[i] += Integer.parseInt(num[j]);
            }
        }

        int answer = result[0];
        for (int i = 1; i < result.length; i++) {
            answer -= result[i];
        }

        System.out.println(answer);
    }
}
