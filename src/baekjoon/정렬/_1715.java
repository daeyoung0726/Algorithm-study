package baekjoon.정렬;

import java.util.*;

public class _1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            pq.add(sc.nextInt());
        }

        int cum = 0;
        while (true) {
            int a = 0;
            int b = 0;
            if (!pq.isEmpty())
                a = pq.poll();
            if (!pq.isEmpty())
                b = pq.poll();
            else {
                break;
            }

            int sum = a + b;
            cum += sum;
            pq.add(sum);
        }

        System.out.println(cum);
    }
}