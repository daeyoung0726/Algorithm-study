package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> positiveNum = new ArrayList<>();
        List<Integer> negativeNum = new ArrayList<>();

        int sum = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] > 1)
                positiveNum.add(arr[i]);
            else if (arr[i] < 1)
                negativeNum.add(arr[i]);
            else
                sum += arr[i];
        }

        Collections.sort(positiveNum, (a, b) -> b - a);
        Collections.sort(negativeNum);

        for (int i = 0; i < positiveNum.size(); i += 2) {
            if (i + 1 < positiveNum.size()) {
                sum += positiveNum.get(i) * positiveNum.get(i + 1);
            } else
                sum += positiveNum.get(i);
        }

        for (int i = 0; i < negativeNum.size(); i += 2) {
            if (i + 1 < negativeNum.size()) {
                sum += negativeNum.get(i) * negativeNum.get(i + 1);
            } else
                sum += negativeNum.get(i);
        }

        System.out.println(sum);
    }
}