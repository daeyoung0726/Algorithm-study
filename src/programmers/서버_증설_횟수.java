package programmers;

// 큐 사용. 넣을 때, 시간, 개수 넣기.
// 현재 개수는 따로 관리. 총 개수도 따로 관리

import java.util.*;

public class 서버_증설_횟수 {
    public int solution(int[] players, int m, int k) {
        Queue<int[]> queue = new LinkedList<>();

        int answer = 0;
        int nowServer = 0;

        for (int i = 0; i < players.length; i++) {

            while (!queue.isEmpty() && i - queue.peek()[1] == k) {
                int[] server = queue.poll();
                nowServer -= server[0];
            }

            int num = players[i] / m - nowServer;
            if (num > 0) {
                queue.add(new int[] {num, i});
                answer += num;
                nowServer += num;
            }
        }
        return answer;
    }
}