package programmers.고득점kit.스택_큐;

import java.util.*;

class 기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {

        int idx = 0;
        int count;
        int total = 0;
        Stack<Integer> stack = new Stack<>();
        while (true) {
            count = 0;
            for (int i = 0; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            if (progresses[idx] >= 100) {
                for (int i = idx; i < progresses.length; i++) {
                    if (progresses[i] >= 100) {
                        count++;
                    } else {
                        idx = i;
                        break;
                    }
                }
                total += count;
                stack.push(count);
            }

            if (total == progresses.length)
                break;
        }
        int[] answer = new int[stack.size()];

        int j = 0;
        for (int x: stack) {
            answer[j++] = x;
        }
        return answer;
    }
}