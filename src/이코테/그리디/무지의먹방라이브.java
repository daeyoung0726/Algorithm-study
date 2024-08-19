package 이코테.그리디;

import java.util.*;

class 무지의먹방라이브 {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        for (int i = 0; i < food_times.length; i++) {
            pq.add(new Point(i+1, food_times[i]));
        }

        long start = 0;
        long previousTime = 0;

        while (!pq.isEmpty()) {
            Point current = pq.peek();
            long timeSpent = (current.time - previousTime) * pq.size();

            if (start + timeSpent > k)
                break;

            start += timeSpent;
            previousTime = current.time;
            while (!pq.isEmpty() && pq.peek().time == current.time) {
                pq.poll();
            }
        }

        if (pq.isEmpty())
            return -1;

        List<Point> list = new ArrayList<>();
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            list.add(p);
        }

        Comparator<Point> cmp = (a, b) -> {
            return a.idx - b.idx;
        };

        Collections.sort(list, cmp);

        answer = list.get((int)((k - start) % list.size())).idx;

        return answer;
    }
}

class Point {
    int idx;
    int time;

    Point(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }
}