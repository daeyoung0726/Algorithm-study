package programmers.고득점kit.스택_큐;

import java.util.*;

class 같은_숫자는_싫어 {
    public int[] solution(int[] arr) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (deque.isEmpty()) {
                deque.add(arr[i]);
            } else {
                if (deque.peekLast() != arr[i])
                    deque.add(arr[i]);
            }
        }

        int[] answer = new int[deque.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = deque.poll();
        }

        return answer;
    }
}