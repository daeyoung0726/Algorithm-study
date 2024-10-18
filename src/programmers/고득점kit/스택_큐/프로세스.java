package programmers.고득점kit.스택_큐;

import java.util.*;

class 프로세스 {
    public int solution(int[] priorities, int location) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.add(priorities[i]);
        }

        int answer = 0;
        int count = 1;
        boolean priorityCheck;
        while (true) {
            int now = queue.poll();
            priorityCheck = true;
            for (int x: queue) {
                if (now < x) {
                    priorityCheck = false;
                    break;
                }
            }

            if (!priorityCheck) {
                queue.add(now);
            } else {
                if (location == 0) {
                    answer = count;
                    break;
                } else {
                    count++;
                }
            }

            location = (location == 0) ? queue.size() - 1 : location - 1;
        }


        return answer;
    }
}