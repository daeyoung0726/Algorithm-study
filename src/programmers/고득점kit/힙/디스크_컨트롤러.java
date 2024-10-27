package programmers.고득점kit.힙;

import java.util.*;

class 디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int time = 0;
        int endJob = 0;
        int startJob = 0;
        int result = 0;
        boolean runJob = false;
        while (true) {
            for (int[] x: jobs) {
                if (x[0] == time) {
                    pq.add(new int[] {x[0], x[1]});
                }
            }

            if (endJob != 0 && endJob == time) {
                runJob = false;
            }

            if (!runJob && !pq.isEmpty()) {
                int[] now = pq.poll();
                endJob = time + now[1];
                runJob = true;
                result += endJob - now[0];
                startJob++;
            }

            if (startJob == jobs.length) {
                break;
            }

            time++;
        }

        return result / jobs.length;
    }
}