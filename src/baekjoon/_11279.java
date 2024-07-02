package baekjoon;

import java.util.*;

public class _11279 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> prq = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num == 0) {
                if (prq.isEmpty())
                    sb.append(0).append('\n');
                else
                    sb.append(prq.remove()).append('\n');
            } else
                prq.add(num);
        }

        System.out.println(sb);

    }
}