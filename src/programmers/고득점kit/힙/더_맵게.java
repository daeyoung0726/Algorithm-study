package programmers.고득점kit.힙;

import java.util.PriorityQueue;

class 더_맵게 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }

        int answer = 0;

        while (true) {
            if (pq.peek() < K) {
                if (pq.size() < 2) {
                    answer = -1;
                    break;
                }
                int x = pq.poll();
                int y = pq.poll();
                int sum = x + 2*y;
                pq.add(sum);
                answer++;
            } else
                break;
        }

        return answer;
    }
}