package programmers.카카오_2022;

// 각 큐의 숫자를 계산. 작은 큐에서 pop 후 다른 큐에 push

import java.util.*;

public class 두_큐_합_같게_만들기 {
    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long q1Sum = 0L;
        long q2Sum = 0L;

        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);

            q1Sum += queue1[i];
            q2Sum += queue2[i];
        }

        if ((q1Sum + q2Sum) % 2 != 0)
            return -1;

        int count = 0;
        int num;
        while (true) {

            if (q1Sum == q2Sum)
                break;

            if (q1Sum > q2Sum) {
                num = q1.poll();
                q2.add(num);
                q1Sum -= num;
                q2Sum += num;
            } else if (q1Sum < q2Sum) {
                num = q2.poll();
                q1.add(num);
                q2Sum -= num;
                q1Sum += num;
            }

            count++;

            if (count >= 3 * queue1.length) {
                count = -1;
                break;
            }

        }

        return count;
    }
}