package baekjoon;

import java.util.*;

public class _11286 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        Comparator<Integer> cmp = (a, b) -> {
            if (Math.abs(a) == Math.abs(b))
                return a - b;
            else
                return Math.abs(a) - Math.abs(b);
        };

        PriorityQueue<Integer> prq = new PriorityQueue<>(cmp);

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