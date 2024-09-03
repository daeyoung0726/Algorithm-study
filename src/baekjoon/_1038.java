package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1038 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n < 10) {
            System.out.println(n);
            return;
        }

        Queue<Long> queue = new LinkedList<>();

        for (int i = 1; i < 10; i++) {
            queue.add((long) i);
        }
        int count = 9;

        long num;
        while (!queue.isEmpty()) {
            num = queue.poll();

            long lastNum = num % 10;

            for (int i = 0; i < lastNum; i++) {
                long nextNum = 10 * num + i;
                count++;
                queue.add(nextNum);

                if (count == n) {
                    System.out.println(nextNum);
                    return;
                }
            }
        }

        System.out.println(-1);
    }
}