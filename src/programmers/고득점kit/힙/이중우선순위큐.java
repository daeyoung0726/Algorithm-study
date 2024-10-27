package programmers.고득점kit.힙;

import java.util.PriorityQueue;

class 이중우선순위큐 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < operations.length; i++) {
            String[] s = operations[i].split(" ");
            if (s[0].equals("I")) {
                if (minPQ.isEmpty()) {
                    minPQ.add(Integer.parseInt(s[1]));
                    maxPQ.add(Integer.parseInt(s[1]));
                } else {
                    minPQ.add(Integer.parseInt(s[1]));
                    maxPQ.add(Integer.parseInt(s[1]));
                }
            }

            if (s[0].equals("D")) {
                if (minPQ.isEmpty()) {
                    continue;
                }

                if (s[1].equals("-1")) {
                    int del = minPQ.poll();
                    maxPQ.remove(del);
                }
                if (s[1].equals("1")) {
                    int del = maxPQ.poll();
                    minPQ.remove(del);
                }
            }
        }

        int min, max;
        if (minPQ.isEmpty()) {
            min = 0;
            max = 0;
        } else{
            min = minPQ.poll();
            max = maxPQ.poll();
        }

        int[] answer = { max, min };
        return answer;
    }
}