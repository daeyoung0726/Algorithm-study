package programmers;

import java.util.*;

public class 야근_지수 {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int work : works) {
            pq.add(work);
        }

        while (n > 0 && !pq.isEmpty()) {
            int num = pq.poll();
            if (num != 0)
                pq.add(num - 1);
            n--;
        }

        long answer = 0;

        while (!pq.isEmpty()) {
            answer += (long) Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}